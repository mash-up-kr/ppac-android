package team.ppac.detail

import android.graphics.Bitmap
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
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.rememberLottieAnimatable
import com.airbnb.lottie.compose.rememberLottieComposition
import kotlinx.coroutines.launch
import team.ppac.common.android.base.BaseComposable
import team.ppac.common.android.component.error.FarmemeErrorScreen
import team.ppac.common.android.util.copyImageToClipBoard
import team.ppac.common.android.util.shareOneLink
import team.ppac.designsystem.R
import team.ppac.detail.mvi.DetailIntent
import team.ppac.detail.mvi.DetailSideEffect
import kotlin.math.roundToInt

@Composable
internal fun DetailRoute(
    modifier: Modifier = Modifier,
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

    BaseComposable(viewModel = viewModel) { uiState ->
        LaunchedEffect(key1 = viewModel) {
            viewModel.sideEffect.collect { sideEffect ->
                when (sideEffect) {
                    is DetailSideEffect.RunRisingEffect -> {
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

                    DetailSideEffect.CopyClipBoard -> {
                        bitmap?.let {
                            context.copyImageToClipBoard(it)
                        }
                    }

                    is DetailSideEffect.ShareLink -> {
                        context.shareOneLink(sideEffect.memeId)
                    }
                }
            }
        }
        if (uiState.isError) {
            FarmemeErrorScreen(
                modifier = Modifier.fillMaxSize(),
                title = "정보를 불러오지 못 했어요.\n새로고침 해주세요.",
                onRetryClick = { viewModel.intent(DetailIntent.CLickRetryButton) }
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
                saveBitmap = saveBitmap
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