package team.ppac.search.detail.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.offset
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
    totalItemCount: Int,
    searchResults: LazyPagingItems<SearchResultUiModel>,
    onMemeClick: (String) -> Unit,
    onCopyClick: (String, String) -> Unit,
) {
    LazyVerticalStaggeredGrid(
        modifier = modifier,
        columns = StaggeredGridCells.Fixed(2),
        horizontalArrangement = Arrangement.spacedBy(space = 12.dp),
        verticalItemSpacing = 20.dp
    ) {
        item(span = StaggeredGridItemSpan.FullLine) {
            SearchDetailResultHeader(totalCount = totalItemCount)
        }
        items(
            count = searchResults.itemCount,
            key = searchResults.itemKey(SearchResultUiModel::memeId)
        ) { index ->
            val searchResult = searchResults[index] ?: return@items

            with(searchResult) {
                FarmemeMemeItem(
                    modifier = Modifier.offset(y = (-20).dp),
                    memeId = memeId,
                    memeTitle = memeTitle,
                    lolCount = lolCount,
                    imageUrl = imageUrl,
                    onMemeClick = onMemeClick,
                    onCopyClick = { onCopyClick(memeId, memeTitle) },
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