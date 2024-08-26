package team.ppac.detail

import android.graphics.Bitmap
import androidx.compose.animation.Crossfade
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.rememberLottieAnimatable
import com.airbnb.lottie.compose.rememberLottieComposition
import kotlinx.coroutines.launch
import team.ppac.analytics.AnalyticsHelper
import team.ppac.analytics.action.MemeDetailAction
import team.ppac.analytics.type.ScreenType
import team.ppac.common.android.base.BaseComposable
import team.ppac.common.android.component.error.FarmemeErrorScreen
import team.ppac.common.android.util.ComposableLifecycle
import team.ppac.common.android.util.copyImageToClipBoard
import team.ppac.common.android.util.shareOneLink
import team.ppac.designsystem.R
import team.ppac.detail.mvi.DetailIntent
import team.ppac.detail.mvi.DetailSideEffect
import kotlin.math.roundToInt

@Composable
internal fun DetailRoute(
    modifier: Modifier = Modifier,
    analyticsHelper: AnalyticsHelper,
    viewModel: DetailViewModel = hiltViewModel(),
    navigateToBack: () -> Unit,
) {
    val context = LocalContext.current

    val lottieComposition by rememberLottieComposition(
        LottieCompositionSpec.RawRes(R.raw.lol_rising_effect)
    )

    val lottieAnimatable = rememberLottieAnimatable()
    var lottiePosition by remember { mutableStateOf(Offset.Zero) }

    var bitmap: Bitmap? by remember { mutableStateOf(null) }

    val saveBitmap: (Bitmap) -> Unit = {
        bitmap = it
    }

    ComposableLifecycle { _, event ->
        when (event) {
            Lifecycle.Event.ON_START -> {
                analyticsHelper.logScreen(ScreenType.MEME_DETAIL)
            }

            else -> {}
        }
    }

    BaseComposable(viewModel = viewModel) { uiState ->
        LaunchedEffect(key1 = viewModel) {
            viewModel.sideEffect.collect { sideEffect ->
                when (sideEffect) {
                    is DetailSideEffect.RunRisingEffect -> {
                        analyticsHelper.logAction(
                            action = MemeDetailAction.CLICK_REACTION,
                            screen = ScreenType.MEME_DETAIL,
                            params = {
                                param("meme_id", uiState.memeId)
                                param("meme_title", uiState.detailMemeUiModel.name)
                            }
                        )
                        launch {
                            lottieAnimatable.animate(
                                composition = lottieComposition,
                                speed = 1.5f
                            )
                        }
                    }

                    is DetailSideEffect.NavigateToBackEffect -> {
                        navigateToBack()
                    }

                    is DetailSideEffect.CopyClipBoard -> {
                        analyticsHelper.logAction(
                            action = MemeDetailAction.CLICK_COPY,
                            screen = ScreenType.MEME_DETAIL,
                            params = {
                                param("meme_id", uiState.memeId)
                                param("meme_title", uiState.detailMemeUiModel.name)
                            }
                        )
                        bitmap?.let {
                            context.copyImageToClipBoard(it)
                        }
                    }

                    is DetailSideEffect.ShareLink -> {
                        analyticsHelper.logAction(
                            action = MemeDetailAction.CLICK_SHARE,
                            screen = ScreenType.MEME_DETAIL,
                            params = {
                                param("meme_id", uiState.memeId)
                                param("meme_title", uiState.detailMemeUiModel.name)
                            }
                        )
                        context.shareOneLink(sideEffect.memeId)
                    }

                    is DetailSideEffect.LogSaveMeme -> {
                        analyticsHelper.logAction(
                            action = MemeDetailAction.CLICK_SAVE,
                            screen = ScreenType.MEME_DETAIL,
                            params = {
                                param("meme_id", uiState.memeId)
                                param("meme_title", uiState.detailMemeUiModel.name)
                            }
                        )
                    }

                    is DetailSideEffect.LogSaveMemeCancel -> {
                        analyticsHelper.logAction(
                            action = MemeDetailAction.CLICK_SAVE_CANCEL,
                            screen = ScreenType.MEME_DETAIL,
                            params = {
                                param("meme_id", uiState.memeId)
                                param("meme_title", uiState.detailMemeUiModel.name)
                            }
                        )
                    }

                    is DetailSideEffect.LogHashTagsClicked -> {
                        analyticsHelper.logAction(
                            action = MemeDetailAction.CLICK_TAG,
                            screen = ScreenType.MEME_DETAIL
                        )
                    }
                }
            }
        }
        Crossfade(targetState = uiState.isError) { isError ->
            if (isError) {
                FarmemeErrorScreen(
                    modifier = Modifier.fillMaxSize(),
                    title = "정보를 불러오지 못 했어요.\n새로고침 해주세요.",
                    onRetryClick = { viewModel.intent(DetailIntent.ClickRetryButton) }
                )
            } else {
                DetailScreen(
                    modifier = modifier,
                    uiState = uiState,
                    onClickFunnyButton = {
                        viewModel.intent(DetailIntent.ClickFunnyButton)
                    },
                    onReactionButtonPosition = {
                        lottiePosition = it
                    },
                    onClickBackButton = {
                        viewModel.intent(DetailIntent.ClickBackButton)
                    },
                    onClickButtonButtons = viewModel::intent,
                    saveBitmap = saveBitmap,
                    onHashTagsClick = { viewModel.intent(DetailIntent.ClickHashtags) }
                )
                LottieAnimation(
                    modifier = Modifier
                        .size(200.dp)
                        .offset {
                            with(lottiePosition) {
                                // ㅋㅋ 버튼의 좌상단 기준으로 사이즈 참고하여 Offset 위치 조정
                                IntOffset(
                                    x = x.roundToInt() + 42.dp.roundToPx(),
                                    y = y.roundToInt() - 192.dp.roundToPx()
                                )
                            }
                        },
                    composition = lottieComposition,
                    progress = { lottieAnimatable.progress },
                )
            }
        }
    }
}