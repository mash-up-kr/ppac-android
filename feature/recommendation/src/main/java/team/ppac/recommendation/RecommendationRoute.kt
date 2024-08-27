package team.ppac.recommendation

import android.graphics.Bitmap
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.toMutableStateList
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.rememberLottieAnimatable
import com.airbnb.lottie.compose.rememberLottieComposition
import kotlinx.coroutines.launch
import team.ppac.analytics.AnalyticsHelper
import team.ppac.analytics.type.ScreenType
import team.ppac.common.android.base.BaseComposable
import team.ppac.common.android.util.ComposableLifecycle
import team.ppac.common.android.util.copyImageToClipBoard
import team.ppac.common.android.util.shareOneLink
import team.ppac.designsystem.R
import team.ppac.domain.model.Meme
import team.ppac.recommendation.mvi.RecommendationIntent
import team.ppac.recommendation.mvi.RecommendationSideEffect

@Composable
internal fun RecommendationRoute(
    analyticsHelper: AnalyticsHelper,
    viewModel: RecommendationViewModel = hiltViewModel(),
) {
    val context = LocalContext.current
    val lottieComposition by rememberLottieComposition(
        LottieCompositionSpec.RawRes(R.raw.lol_rising_effect)
    )
    val lottieAnimatable = rememberLottieAnimatable()

    BaseComposable(viewModel = viewModel) { state ->
        val memeBitmap = remember(state.thisWeekMemes.size) {
            state.thisWeekMemes.map<Meme, Bitmap?> { null }.toMutableStateList()
        }

        ComposableLifecycle { _, event ->
            when (event) {
                Lifecycle.Event.ON_START -> {
                    analyticsHelper.reportScreen(ScreenType.RECOMMENDATION)
                }

                else -> {}
            }
        }

        LaunchedEffect(viewModel, memeBitmap) {
            viewModel.sideEffect.collect { sideEffect ->
                when (sideEffect) {
                    RecommendationSideEffect.RunRisingEffect -> {
                        launch {
                            lottieAnimatable.animate(
                                composition = lottieComposition,
                                speed = 1.5f
                            )
                        }
                    }

                    is RecommendationSideEffect.CopyClipBoard -> {
                        memeBitmap[sideEffect.memeIndex]?.let {
                            context.copyImageToClipBoard(bitmap = it)
                        }
                    }

                    is RecommendationSideEffect.ShareLink -> {
                        context.shareOneLink(sideEffect.memeId)
                    }
                }
            }
        }

        RecommendationScreen(
            state = state,
            lottieAnimatable = lottieAnimatable,
            lottieComposition = lottieComposition,
            onPullToRefresh = { viewModel.intent(RecommendationIntent.PullRefresh) },
            onRetryClick = { viewModel.intent(RecommendationIntent.Init) },
            onScrollPager = { page, meme ->
                viewModel.intent(RecommendationIntent.MovePage(meme, page))
            },
            onLoadMeme = { index, bitmap ->
                memeBitmap[index] = bitmap
            },
            onActionButtonsIntentClick = viewModel::intent,
        )
    }
}