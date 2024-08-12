package team.ppac.recommendation.component

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.boundsInWindow
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.unit.dp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.rememberLottieAnimatable
import com.airbnb.lottie.compose.rememberLottieComposition
import kotlinx.coroutines.launch
import team.ppac.designsystem.FarmemeTheme
import team.ppac.designsystem.R
import team.ppac.designsystem.component.button.FarmemeCircleButton
import team.ppac.designsystem.component.button.FarmemeWeakButton
import team.ppac.designsystem.foundation.FarmemeIcon
import team.ppac.domain.model.Meme
import team.ppac.recommendation.mvi.RecommendationIntent

@Composable
internal fun ActionButtons(
    modifier: Modifier = Modifier,
    meme: Meme,
    page: Int,
    onClickIntent: (RecommendationIntent.ClickButton) -> Unit,
    onReactionButtonPositioned: (Offset) -> Unit,
) {
    val lottieComposition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.lol_move_effect))
    val coroutineScope = rememberCoroutineScope()
    val lottieAnimatable = rememberLottieAnimatable()

    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        FarmemeWeakButton(
            modifier = Modifier
                .height(50.dp)
                .weight(1f)
                .onGloballyPositioned {
                    val bound = it.boundsInWindow()
                    onReactionButtonPositioned(bound.topLeft)
                },
            backgroundColor = FarmemeTheme.backgroundColor.white,
            text = "",
            textColor = Color.Unspecified,
            icon = {
                if (meme.reactionCount == 0) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(
                            space = 2.dp,
                            alignment = Alignment.CenterHorizontally,
                        )
                    ) {
                        FarmemeIcon.Lol()
                        FarmemeIcon.SoFunny()
                    }
                }
                AnimatedVisibility(
                    visible = meme.reactionCount > 0,
                    enter = fadeIn(),
                    exit = fadeOut(),
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(
                            space = 2.dp,
                            alignment = Alignment.CenterHorizontally,
                        )
                    ) {
                        if (meme.isReaction) {
                            LottieAnimation(
                                modifier = Modifier.size(
                                    height = 22.dp,
                                    width = 44.dp,
                                ),
                                composition = lottieComposition,
                                progress = { lottieAnimatable.progress },
                            )
                        } else {
                            FarmemeIcon.KKHorizon(
                                modifier = Modifier.size(
                                    height = 22.dp,
                                    width = 44.dp
                                )
                            )
                        }
                        Text(
                            text = "+${meme.reactionCount}",
                            style = FarmemeTheme.typography.highlight.basic,
                            color = if (meme.isReaction) {
                                FarmemeTheme.textColor.brand
                            } else {
                                FarmemeTheme.textColor.primary
                            }
                        )
                    }

                }
            },
            onClick = {
                coroutineScope.launch {
                    lottieAnimatable.animate(composition = lottieComposition)
                }
                onClickIntent(RecommendationIntent.ClickButton.LoL(meme))
            }
        )
        FarmemeCircleButton(
            backgroundColor = FarmemeTheme.backgroundColor.white,
            icon = { FarmemeIcon.Stroke() },
            onClick = {
                onClickIntent(RecommendationIntent.ClickButton.Copy(page))
            }
        )

        FarmemeCircleButton(
            backgroundColor = FarmemeTheme.backgroundColor.white,
            icon = { FarmemeIcon.Share() },
            onClick = {
                onClickIntent(RecommendationIntent.ClickButton.Share(meme))
            }
        )

        FarmemeCircleButton(
            backgroundColor = FarmemeTheme.backgroundColor.white,
            icon = {
                if (meme.isSaved) {
                    FarmemeIcon.BookmarkFilled()
                } else {
                    FarmemeIcon.BookmarkLine()
                }
            },
            onClick = {
                onClickIntent(RecommendationIntent.ClickButton.BookMark(meme))
            }
        )
    }
}