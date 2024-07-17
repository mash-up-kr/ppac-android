package team.ppac.mypage.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlinx.collections.immutable.ImmutableList
import team.ppac.designsystem.FarmemeTheme
import team.ppac.designsystem.component.list.FarmemeListHeader
import team.ppac.designsystem.foundation.FarmemeIcon
import team.ppac.domain.model.Meme
import team.ppac.mypage.mvi.MyPageUiState

@Composable
internal fun RecentMemeContent(
    modifier: Modifier = Modifier,
    recentMemes: ImmutableList<Meme>,
    onClickMemeItem: (String) -> Unit,
) {
    Column(
        modifier = modifier.fillMaxWidth(),
    ) {
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(10.dp)
                .background(FarmemeTheme.skeletonColor.primary),
        )
        Spacer(modifier = Modifier.height(20.dp))
        FarmemeListHeader(
            title = "최근 본 밈",
            leadingIcon = {
                FarmemeIcon.SuccessOutlined(Modifier.size(20.dp))
            },
        )
        if (recentMemes.isNotEmpty()) {
            RecentMemeList(
                recentMemes = recentMemes,
                onClickMemeItem = onClickMemeItem,
            )
        } else {
            RecentMemeEmpty()
        }
        Spacer(modifier = Modifier.height(50.dp))
    }
}

@Composable
private fun RecentMemeList(
    modifier: Modifier = Modifier,
    recentMemes: ImmutableList<Meme>,
    onClickMemeItem: (String) -> Unit,
) {
    LazyRow(
        modifier = modifier.fillMaxWidth(),
        contentPadding = PaddingValues(horizontal = 20.dp),
        horizontalArrangement = Arrangement.spacedBy(10.dp),
    ) {
        items(items = recentMemes) { meme ->
            RecentMemeCard(
                imageUrl = meme.imageUrl,
                onClick = { onClickMemeItem(meme.id) },
            )
        }
    }
}

@Composable
private fun RecentMemeEmpty(
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Spacer(modifier = Modifier.height(50.dp))
        Text(
            text = "최근 본 밈이 없어요",
            style = FarmemeTheme.typography.body.large.medium,
            color = FarmemeTheme.textColor.assistive,
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun RecentMemeContentPreview() {
    RecentMemeContent(
        recentMemes = MyPageUiState.INITIAL_STATE.recentMemes,
        onClickMemeItem = {}
    )
}