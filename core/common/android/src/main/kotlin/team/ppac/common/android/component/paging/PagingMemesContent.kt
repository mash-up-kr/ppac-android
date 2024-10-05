package team.ppac.common.android.component.paging

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridItemSpan
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.PagingData
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemKey
import kotlinx.coroutines.flow.emptyFlow
import team.ppac.common.android.component.FarmemeMemeItem
import team.ppac.domain.model.Meme

@Composable
fun PagingMemesContent(
    modifier: Modifier = Modifier,
    totalItemCount: Int,
    pagingItems: LazyPagingItems<Meme>,
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
            count = pagingItems.itemCount,
            key = pagingItems.itemKey(Meme::id)
        ) { index ->
            val searchResult = pagingItems[index] ?: return@items

            with(searchResult) {
                FarmemeMemeItem(
                    modifier = Modifier.offset(y = (-20).dp),
                    memeId = id,
                    memeTitle = title,
                    lolCount = reactionCount,
                    imageUrl = imageUrl,
                    onMemeClick = onMemeClick,
                    onCopyClick = { onCopyClick(id, title) },
                )
            }
        }

        pagingItems.apply {
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

@Preview
@Composable
private fun PagingMemesContentPreview() {
    PagingMemesContent(
        totalItemCount = 10,
        pagingItems = emptyFlow<PagingData<Meme>>().collectAsLazyPagingItems(),
        onMemeClick = {},
        onCopyClick = { _, _ -> }
    )
}