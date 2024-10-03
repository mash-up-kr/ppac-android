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
import team.ppac.analytics.action.MEME_ID
import team.ppac.analytics.action.MEME_TITLE
import team.ppac.analytics.action.RecommendationAction
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
    navigateToRegister: () -> Unit,
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
                    is RecommendationSideEffect.RunRisingEffect -> {
                        analyticsHelper.reportAction(
                            action = RecommendationAction.CLICK_REACTION,
                            screen = ScreenType.RECOMMENDATION,
                            params = {
                                with(sideEffect.meme) {
                                    param(MEME_ID, id)
                                    param(MEME_TITLE, title)
                                }
                            }
                        )

                        launch {
                            lottieAnimatable.animate(
                                composition = lottieComposition,
                                speed = 1.5f
                            )
                        }
                    }

                    is RecommendationSideEffect.CopyClipBoard -> {
                        val selectedMeme = state.thisWeekMemes[sideEffect.selectedMemeIndex]
                        analyticsHelper.reportAction(
                            action = RecommendationAction.CLICK_COPY,
                            screen = ScreenType.RECOMMENDATION,
                            params = {
                                param(MEME_ID, selectedMeme.id)
                                param(MEME_TITLE, selectedMeme.title)
                            }
                        )

                        memeBitmap[sideEffect.selectedMemeIndex]?.let {
                            context.copyImageToClipBoard(bitmap = it)
                        }
                    }

                    is RecommendationSideEffect.ShareLink -> {
                        val sharedMeme = state.thisWeekMemes
                            .firstOrNull { it.id == sideEffect.memeId }

                        if (sharedMeme != null) {
                            analyticsHelper.reportAction(
                                action = RecommendationAction.CLICK_SHARE,
                                screen = ScreenType.RECOMMENDATION,
                                params = {
                                    param(MEME_ID, sharedMeme.id)
                                    param(MEME_TITLE, sharedMeme.title)
                                }
                            )
                        }

                        context.shareOneLink(sideEffect.memeId)
                    }

                    is RecommendationSideEffect.LogSaveMeme -> {
                        analyticsHelper.reportAction(
                            action = RecommendationAction.CLICK_SAVE,
                            screen = ScreenType.RECOMMENDATION,
                            params = {
                                param(MEME_ID, sideEffect.meme.id)
                                param(MEME_TITLE, sideEffect.meme.title)
                            }
                        )
                    }

                    is RecommendationSideEffect.LogSaveMemeCancel -> {
                        analyticsHelper.reportAction(
                            action = RecommendationAction.CLICK_SAVE_CANCEL,
                            screen = ScreenType.RECOMMENDATION,
                            params = {
                                param(MEME_ID, sideEffect.meme.id)
                                param(MEME_TITLE, sideEffect.meme.title)
                            }
                        )
                    }

                    is RecommendationSideEffect.LogHashTagsClicked -> {
                        analyticsHelper.reportAction(
                            action = RecommendationAction.CLICK_TAG,
                            screen = ScreenType.RECOMMENDATION,
                        )
                    }

                    RecommendationSideEffect.NavigateToRegister -> {
                        navigateToRegister()
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
                analyticsHelper.reportAction(
                    action = RecommendationAction.SWIPE_MEME,
                    screen = ScreenType.RECOMMENDATION,
                    params = {
                        param(MEME_ID, meme.id)
                        param(MEME_TITLE, meme.title)
                    }
                )

                analyticsHelper.reportAction(
                    action = RecommendationAction.VIEW_MEME,
                    screen = ScreenType.RECOMMENDATION
                )

                viewModel.intent(RecommendationIntent.MovePage(meme, page))
            },
            onLoadMeme = { index, bitmap ->
                memeBitmap[index] = bitmap
            },
            onActionButtonsIntentClick = viewModel::intent,
            onUpload = {
                viewModel.intent(RecommendationIntent.ClickUpload)
            }
        )
    }
}