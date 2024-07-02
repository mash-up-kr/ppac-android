package team.ppac.search.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlinx.collections.immutable.persistentListOf
import team.ppac.designsystem.FarmemeTheme
import team.ppac.search.model.CategoryUiModel

@Composable
internal fun MemeCategoryContent(
    modifier: Modifier = Modifier,
    uiModel: CategoryUiModel,
    onCategoryClick: () -> Unit,
) {
    Column(
        modifier = modifier.fillMaxWidth()
    ) {
        MemeCategoryHeader(title = uiModel.categoryHeader)
        MemeCategoryChips(
            modifier = Modifier.padding(horizontal = 20.dp),
            categories = uiModel.categories,
            onCategoryClick = onCategoryClick
        )
    }
}

@Preview
@Composable
private fun MemeCategoryContentPreview() {
    Box(modifier = Modifier.background(FarmemeTheme.backgroundColor.white)) {
        MemeCategoryContent(
            uiModel = CategoryUiModel(
                categoryHeader = "감정",
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
            ),
            onCategoryClick = {}
        )
    }
}