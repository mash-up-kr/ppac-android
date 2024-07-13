package team.ppac.recommendation.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.collections.immutable.ImmutableList
import team.ppac.designsystem.FarmemeTheme
import team.ppac.domain.model.Keyword

@Composable
internal fun KeyWordsRow(
    modifier: Modifier = Modifier,
    keywords: ImmutableList<Keyword>
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(4.dp, Alignment.CenterHorizontally)
    ) {
        keywords.forEach {
            Text(
                text = "#${it.name}",
                style = FarmemeTheme.typography.body.medium.medium,
                color = FarmemeTheme.textColor.secondary,
            )
        }
    }
}