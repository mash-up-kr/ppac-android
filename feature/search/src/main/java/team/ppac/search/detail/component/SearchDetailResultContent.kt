package team.ppac.search.detail.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.itemKey
import team.ppac.common.android.component.FarmemeMemeItem
import team.ppac.search.detail.model.SearchResultUiModel

@Composable
fun SearchDetailResultContent(
    modifier: Modifier = Modifier,
    searchResults: LazyPagingItems<SearchResultUiModel>,
    onMemeClick: (String) -> Unit,
) {
    LazyVerticalStaggeredGrid(
        modifier = modifier,
        columns = StaggeredGridCells.Fixed(2),
        horizontalArrangement = Arrangement.spacedBy(space = 12.dp),
        verticalItemSpacing = 20.dp
    ) {
        items(
            count = searchResults.itemCount,
            key = searchResults.itemKey(SearchResultUiModel::memeId)
        ) { index ->
            val seachResult = searchResults[index] ?: throw Exception()

            FarmemeMemeItem(
                modifier = Modifier,
                memeId = seachResult.memeId,
                memeTitle = seachResult.memeTitle,
                lolCount = seachResult.lolCount,
                imageUrl = seachResult.imageUrl,
                onMemeClick = onMemeClick,
                onCopyClick = {}, // TODO(JaesungLeee) : 스낵바 띄우기
            )
        }
    }
}