package team.ppac.search.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf
import team.ppac.designsystem.FarmemeTheme
import team.ppac.designsystem.foundation.FarmemeRadius
import team.ppac.designsystem.util.extension.noRippleClickable

@Composable
internal fun MemeCategoryChip(
    modifier: Modifier = Modifier,
    title: String,
    onClick: () -> Unit,
) {
    Box(
        modifier = modifier
            .clip(FarmemeRadius.Radius20.shape)
            .background(
                color = FarmemeTheme.backgroundColor.assistive,
                shape = FarmemeRadius.Radius20.shape
            )
            .noRippleClickable(onClick = onClick)
            .padding(horizontal = 16.dp, vertical = 9.5.dp)
    ) {
        Text(
            text = title,
            style = FarmemeTheme.typography.body.medium.medium.copy(
                color = FarmemeTheme.textColor.primary
            )
        )
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
internal fun MemeCategoryChips(
    modifier: Modifier = Modifier,
    categories: ImmutableList<String>,
    onCategoryClick: () -> Unit,
) {
    FlowRow(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(8.dp, Alignment.Start),
        verticalArrangement = Arrangement.spacedBy(8.dp, Alignment.Top),
    ) {
        repeat(categories.size) { index ->
            val category = categories[index]
            MemeCategoryChip(
                title = category,
                onClick = onCategoryClick
            )
        }
    }
}

@Preview
@Composable
private fun MemeCategoryChipPreview() {
    MemeCategoryChip(
        title = "현타",
        onClick = {}
    )
}

@Preview
@Composable
private fun MemeCategoryRowPreview() {
    Box(modifier = Modifier.background(FarmemeTheme.backgroundColor.white)) {
        MemeCategoryChips(
            categories = persistentListOf(
                "행복",
                "슬픈",
                "분노",
                "웃긴",
                "피곤",
                "절망",
                "현타",
                "당황",
                "무념무상",
            ),
            onCategoryClick = {}
        )
    }
}
