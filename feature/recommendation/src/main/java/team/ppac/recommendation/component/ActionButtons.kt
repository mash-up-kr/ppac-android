package team.ppac.recommendation.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import team.ppac.designsystem.FarmemeTheme
import team.ppac.designsystem.component.button.FarmemeCircleButton
import team.ppac.designsystem.component.button.FarmemeWeakButton
import team.ppac.designsystem.foundation.FarmemeIcon
import team.ppac.domain.model.Meme
import team.ppac.recommendation.mvi.RecommendationIntent

@Composable
internal fun ActionButtons(
    modifier: Modifier = Modifier,
    meme: Meme,
    onClickIntent: (RecommendationIntent.ClickButton) -> Unit,
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        FarmemeWeakButton(
            modifier = Modifier.weight(1f),
            backgroundColor = FarmemeTheme.backgroundColor.white,
            text = "",
            textColor = Color.Unspecified,
            icon = {
                Row {
                    FarmemeIcon.Lol()
                    FarmemeIcon.SoFunny()
                }
            },
            onClick = {
                onClickIntent(RecommendationIntent.ClickButton.LoL(meme))
            }
        )
        FarmemeCircleButton(
            backgroundColor = FarmemeTheme.backgroundColor.white,
            icon = { FarmemeIcon.Stroke() },
            onClick = {
                onClickIntent(RecommendationIntent.ClickButton.Copy(meme))
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
            icon = { FarmemeIcon.BookmarkLine() },
            onClick = {
                onClickIntent(RecommendationIntent.ClickButton.BookMark(meme))
            }
        )
    }
}