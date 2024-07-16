package team.ppac.mypage.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlinx.collections.immutable.ImmutableList
import team.ppac.common.android.component.FarmemeMemeItem
import team.ppac.designsystem.FarmemeTheme
import team.ppac.designsystem.component.list.FarmemeListHeader
import team.ppac.designsystem.foundation.FarmemeIcon
import team.ppac.domain.model.Meme
import team.ppac.mypage.mvi.MyPageUiState

@Composable
internal fun SavedMemeContent(
    modifier: Modifier = Modifier,
    savedMemes: ImmutableList<Meme>,
    onMemeItemClick: (String) -> Unit,
) {
    Column(
        modifier = modifier.fillMaxWidth(),
    ) {
        FarmemeListHeader(
            title = "나의 파밈함",
            leadingIcon = {
                FarmemeIcon.BookmarkLine(Modifier.size(20.dp))
            },
        )
        if (savedMemes.size > 0) {
            SavedMemeList(
                savedMemes = savedMemes,
                onMemeItemClick = onMemeItemClick,
            )
        } else {
            SavedMemeEmpty()
        }
        Spacer(modifier = Modifier.height(50.dp))
    }
}

@Composable
private fun SavedMemeList(
    modifier: Modifier = Modifier,
    savedMemes: ImmutableList<Meme>,
    onMemeItemClick: (String) -> Unit,
) {
    LazyVerticalStaggeredGrid(
        modifier = modifier
            .fillMaxWidth()
            .heightIn(max = (150 * savedMemes.size).dp) // TODO(ze-zeh) : 아이템 최대 높이 조정(변경 필요)
            .wrapContentHeight(),
        userScrollEnabled = false,
        columns = StaggeredGridCells.Fixed(2),
        horizontalArrangement = Arrangement.spacedBy(space = 12.dp),
        contentPadding = PaddingValues(horizontal = 20.dp),
        verticalItemSpacing = 20.dp,
    ) {
        items(items = savedMemes) { meme ->
            FarmemeMemeItem(
                modifier = Modifier,
                memeId = meme.id,
                memeTitle = meme.title,
                lolCount = 0,
                imageUrl = meme.imageUrl,
                onMemeClick = onMemeItemClick,
                onCopyClick = {}, // TODO(ze-zeh) : 스낵바 띄우기
            )
        }
    }
}

@Composable
private fun SavedMemeEmpty(
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Spacer(modifier = Modifier.height(50.dp))
        Text(
            text = "저장한 밈이 없어요",
            style = FarmemeTheme.typography.body.large.medium,
            color = FarmemeTheme.textColor.assistive,
        )
        Spacer(modifier = Modifier.height(50.dp))
    }
}

@Preview
@Composable
internal fun SavedMemeContentPreview() {
    SavedMemeContent(
        savedMemes = MyPageUiState.INITIAL_STATE.savedMemes,
        onMemeItemClick = {},
    )
}