package team.ppac.detail

import android.graphics.Bitmap
import androidx.compose.animation.Crossfade
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
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
import team.ppac.analytics.action.MEME_ID
import team.ppac.analytics.action.MEME_TITLE
import team.ppac.analytics.action.MemeDetailAction
import team.ppac.analytics.type.ScreenType
import team.ppac.common.android.base.BaseComposable
import team.ppac.common.android.component.error.FarmemeErrorScreen
import team.ppac.common.android.util.ComposableLifecycle
import team.ppac.common.android.util.copyImageToClipBoard
import team.ppac.common.android.util.shareOneLink
import team.ppac.designsystem.FarmemeTheme
import team.ppac.designsystem.R
import team.ppac.designsystem.component.dialog.FarmemeBottomSheetDialog
import team.ppac.detail.mvi.DetailIntent
import team.ppac.detail.mvi.DetailSideEffect
import team.ppac.detail.util.DetailScreenSize
import kotlin.math.roundToInt

@Composable
internal fun DetailRoute(
    modifier: Modifier = Modifier,
    analyticsHelper: AnalyticsHelper,
    viewModel: DetailViewModel = hiltViewModel(),
    navigateToBack: () -> Unit,
) {
    val context = LocalContext.current
    val configuration = LocalConfiguration.current

    val currentDetailScreenSize =
        DetailScreenSize.from(configuration.screenWidthDp.dp, configuration.screenHeightDp.dp)
    val lottieComposition by rememberLottieComposition(
        LottieCompositionSpec.RawRes(R.raw.lol_rising_effect)
    )

    val lottieAnimatable = rememberLottieAnimatable()
    var lottiePosition by remember { mutableStateOf(Offset.Zero) }

    var bitmap: Bitmap? by remember { mutableStateOf(null) }

    val saveBitmap: (Bitmap) -> Unit = {
        bitmap = it
    }
    BaseComposable(viewModel = viewModel) { uiState ->
        ComposableLifecycle { _, event ->
            when (event) {
                Lifecycle.Event.ON_START -> {
                    analyticsHelper.reportScreen(ScreenType.MEME_DETAIL)
                    analyticsHelper.reportAction(
                        action = MemeDetailAction.VIEW_MEME,
                        screen = ScreenType.MEME_DETAIL,
                        params = {
                            param(MEME_ID, uiState.memeId)
                            param(MEME_TITLE, uiState.detailMemeUiModel.name)
                        }
                    )
                }

                else -> {}
            }
        }

        LaunchedEffect(key1 = viewModel) {
            viewModel.sideEffect.collect { sideEffect ->
                when (sideEffect) {
                    is DetailSideEffect.RunRisingEffect -> {
                        analyticsHelper.reportAction(
                            action = MemeDetailAction.CLICK_REACTION,
                            screen = ScreenType.MEME_DETAIL,
                            params = {
                                param(MEME_ID, uiState.memeId)
                                param(MEME_TITLE, uiState.detailMemeUiModel.name)
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
                        analyticsHelper.reportAction(
                            action = MemeDetailAction.CLICK_COPY,
                            screen = ScreenType.MEME_DETAIL,
                            params = {
                                param(MEME_ID, uiState.memeId)
                                param(MEME_TITLE, uiState.detailMemeUiModel.name)
                            }
                        )
                        bitmap?.let {
                            context.copyImageToClipBoard(it)
                        }
                    }

                    is DetailSideEffect.ShareLink -> {
                        analyticsHelper.reportAction(
                            action = MemeDetailAction.CLICK_SHARE,
                            screen = ScreenType.MEME_DETAIL,
                            params = {
                                param(MEME_ID, uiState.memeId)
                                param(MEME_TITLE, uiState.detailMemeUiModel.name)
                            }
                        )
                        context.shareOneLink(sideEffect.memeId)
                    }

                    is DetailSideEffect.LogSaveMeme -> {
                        analyticsHelper.reportAction(
                            action = MemeDetailAction.CLICK_SAVE,
                            screen = ScreenType.MEME_DETAIL,
                            params = {
                                param(MEME_ID, uiState.memeId)
                                param(MEME_TITLE, uiState.detailMemeUiModel.name)
                            }
                        )
                    }

                    is DetailSideEffect.LogSaveMemeCancel -> {
                        analyticsHelper.reportAction(
                            action = MemeDetailAction.CLICK_SAVE_CANCEL,
                            screen = ScreenType.MEME_DETAIL,
                            params = {
                                param(MEME_ID, uiState.memeId)
                                param(MEME_TITLE, uiState.detailMemeUiModel.name)
                            }
                        )
                    }

                    is DetailSideEffect.LogHashTagsClicked -> {
                        analyticsHelper.reportAction(
                            action = MemeDetailAction.CLICK_TAG,
                            screen = ScreenType.MEME_DETAIL
                        )
                    }
                }
            }
        }

        if (uiState.showOptionBottomSheet) {
            FarmemeBottomSheetDialog(
                onBottomSheetDismiss = { viewModel.intent(DetailIntent.ClickBottomSheetDismiss) }
            ) {
                Text(
                    modifier = Modifier
                        .padding(vertical = 15.dp)
                        .background(FarmemeTheme.backgroundColor.white),
                    text = "신고하기",
                    textAlign = TextAlign.Center
                )
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
                    onHashTagsClick = { viewModel.intent(DetailIntent.ClickHashtags) },
                    onOptionClick = { viewModel.intent(DetailIntent.ClickOption) },
                    currentDetailScreenSize = currentDetailScreenSize,
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