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
internal fun RegisteredMemeContent(
    modifier: Modifier = Modifier,
    registeredMemes: LazyPagingItems<Meme>,
    onMemeClick: (String) -> Unit,
    onCopyClick: (Meme) -> Unit,
    onRegisterClick: () -> Unit,
) {
    Column(
        modifier = modifier.fillMaxWidth(),
    ) {
        if (registeredMemes.itemCount > 0) {
            RegisteredMemeList(
                registeredMemes = registeredMemes,
                onMemeItemClick = onMemeClick,
                onCopyClick = onCopyClick
            )
        } else {
            EmptyMemeContent(
                tabType = MyPageTabType.REGISTERED_MEMES,
                onUploadClick = onRegisterClick,
            )
        }
        Spacer(modifier = Modifier.height(50.dp))
    }
}

@Composable
private fun RegisteredMemeList(
    modifier: Modifier = Modifier,
    registeredMemes: LazyPagingItems<Meme>,
    onMemeItemClick: (String) -> Unit,
    onCopyClick: (Meme) -> Unit,
) {
    LazyVerticalStaggeredGrid(
        modifier = modifier
            .fillMaxWidth()
            .heightIn(max = (FARMEME_MEME_ITEM_MAX_HEIGHT * registeredMemes.itemCount).dp)
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
        items(count = registeredMemes.itemCount) { index ->
            val meme = registeredMemes[index]

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
private fun RegisteredMemeContentPreview() {
    RegisteredMemeContent(
        registeredMemes = flowOf(PagingData.empty<Meme>()).collectAsLazyPagingItems(),
        onMemeClick = {},
        onCopyClick = {},
        onRegisterClick = {},
    )
}