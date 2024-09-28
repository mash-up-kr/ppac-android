package team.ppac.mypage.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.paging.PagingData
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import kotlinx.coroutines.flow.flowOf
import team.ppac.common.android.component.FARMEME_MEME_ITEM_MAX_HEIGHT
import team.ppac.common.android.component.FarmemeMemeItem
import team.ppac.domain.model.Meme
import team.ppac.mypage.mvi.MyPageTabType

@Composable
internal fun MyMemeContent(
    modifier: Modifier = Modifier,
    myMemes: LazyPagingItems<Meme>,
    onMemeClick: (String) -> Unit,
    onCopyClick: (Meme) -> Unit,
    onUploadClick: () -> Unit,
) {
    Column(
        modifier = modifier.fillMaxWidth(),
    ) {
        if (myMemes.itemCount > 0) {
            MyMemeList(
                myMemes = myMemes,
                onMemeItemClick = onMemeClick,
                onCopyClick = onCopyClick
            )
        } else {
            EmptyMemeContent(
                tabType = MyPageTabType.MY_MEMES,
                onUploadClick = onUploadClick,
            )
        }
        Spacer(modifier = Modifier.height(50.dp))
    }
}

@Composable
private fun MyMemeList(
    modifier: Modifier = Modifier,
    myMemes: LazyPagingItems<Meme>,
    onMemeItemClick: (String) -> Unit,
    onCopyClick: (Meme) -> Unit,
) {
    LazyVerticalStaggeredGrid(
        modifier = modifier
            .fillMaxWidth()
            .heightIn(max = (FARMEME_MEME_ITEM_MAX_HEIGHT * myMemes.itemCount).dp)
            .wrapContentHeight(),
        userScrollEnabled = false,
        columns = StaggeredGridCells.Fixed(2),
        horizontalArrangement = Arrangement.spacedBy(space = 12.dp),
        contentPadding = PaddingValues(
            start = 20.dp,
            end = 20.dp,
        ),
        verticalItemSpacing = 20.dp,
    ) {
        items(count = myMemes.itemCount) { index ->
            val meme = myMemes[index]

            if (meme != null) {
                FarmemeMemeItem(
                    modifier = Modifier,
                    memeId = meme.id,
                    memeTitle = meme.title,
                    lolCount = 0,
                    imageUrl = meme.imageUrl,
                    onMemeClick = onMemeItemClick,
                    onCopyClick = {
                        onCopyClick(meme)
                    },
                )
            }
        }
    }
}

@Preview
@Composable
private fun MyMemeContentPreview() {
    MyMemeContent(
        myMemes = flowOf(PagingData.empty<Meme>()).collectAsLazyPagingItems(),
        onMemeClick = {},
        onCopyClick = {},
        onUploadClick = {},
    )
}