package team.ppac.search.detail.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.collections.immutable.ImmutableList
import team.ppac.common.android.component.FarmemeMemeItem
import team.ppac.search.detail.model.SearchResultUiModel

@Composable
fun SearchDetailResultContent(
    modifier: Modifier = Modifier,
    searchResults: ImmutableList<SearchResultUiModel>,
    onMemeClick: (String) -> Unit,
) {
    LazyVerticalStaggeredGrid(
        modifier = modifier,
        columns = StaggeredGridCells.Fixed(2),
        horizontalArrangement = Arrangement.spacedBy(space = 12.dp),
        verticalItemSpacing = 20.dp
    ) {
        items(items = searchResults) { uiModel ->
            FarmemeMemeItem(
                modifier = Modifier,
                memeId = uiModel.memeId,
                memeTitle = uiModel.memeTitle,
                lolCount = uiModel.lolCount,
                imageUrl = uiModel.imageUrl,
                onMemeClick = onMemeClick,
            )
        }
    }
}