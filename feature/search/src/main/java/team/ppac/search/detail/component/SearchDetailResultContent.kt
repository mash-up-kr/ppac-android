package team.ppac.search.detail.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridItemSpan
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.itemKey
import team.ppac.common.android.component.FarmemeMemeItem
import team.ppac.search.detail.model.SearchResultUiModel

@Composable
fun SearchDetailResultContent(
    modifier: Modifier = Modifier,
    searchResults: LazyPagingItems<SearchResultUiModel>,
    onMemeClick: (String) -> Unit,
    onCopyClick: () -> Unit,
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
            val searchResult = searchResults[index] ?: throw Exception()

            with(searchResult) {
                FarmemeMemeItem(
                    modifier = Modifier,
                    memeId = memeId,
                    memeTitle = memeTitle,
                    lolCount = lolCount,
                    imageUrl = imageUrl,
                    onMemeClick = onMemeClick,
                    onCopyClick = onCopyClick,
                )
            }
        }

        searchResults.apply {
            when {
                loadState.append is LoadState.NotLoading && loadState.append.endOfPaginationReached -> {
                    item(span = StaggeredGridItemSpan.FullLine) {
                        SearchDetailResultFooter()
                    }
                }
            }
        }
    }
}