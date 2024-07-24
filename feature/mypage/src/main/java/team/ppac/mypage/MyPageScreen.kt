package team.ppac.mypage

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.paging.compose.collectAsLazyPagingItems
import team.ppac.designsystem.FarmemeTheme
import team.ppac.designsystem.R
import team.ppac.designsystem.component.scaffold.FarmemeScaffold
import team.ppac.designsystem.component.scaffold.type.BackgroundColorType
import team.ppac.designsystem.component.tabbar.TabBarHeight
import team.ppac.designsystem.component.toolbar.FarmemeActionToolBar
import team.ppac.mypage.component.MyPageLevelBox
import team.ppac.mypage.component.MyPageProgressBar
import team.ppac.mypage.component.MyPagePullRefreshIndicator
import team.ppac.mypage.component.MyPageSpeechBubble
import team.ppac.mypage.component.RecentMemeContent
import team.ppac.mypage.component.SavedMemeContent
import team.ppac.mypage.model.LevelUiModel
import team.ppac.mypage.model.MyPageLevel
import team.ppac.mypage.mvi.MyPageUiState

@OptIn(ExperimentalMaterialApi::class)
@Composable
internal fun MyPageScreen(
    uiState: MyPageUiState,
    onRefreshData: () -> Unit,
    onSettingClick: () -> Unit,
    onCopyClick: () -> Unit,
    onRecentMemeClick: (String) -> Unit,
    onSavedMemeClick: (String) -> Unit,
) {
    val savedMemes = uiState.savedMemes.collectAsLazyPagingItems()
    val pullRefreshState = rememberPullRefreshState(
        refreshing = uiState.isRefreshing,
        onRefresh = onRefreshData,
    )

    FarmemeScaffold(
        modifier = Modifier
            .fillMaxSize()
            .pullRefresh(pullRefreshState),
        backgroundColorType = BackgroundColorType.SolidColor(FarmemeTheme.backgroundColor.white),
    ) {
        Column(
            modifier = Modifier
                .padding(bottom = TabBarHeight)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            MyPageBody(
                levelUiModel = uiState.levelUiModel,
                onSettingClick = onSettingClick,
            )
            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(10.dp)
                    .background(FarmemeTheme.skeletonColor.primary),
            )
            RecentMemeContent(
                recentMemes = uiState.recentMemes,
                onMemeClick = onRecentMemeClick,
            )
            SavedMemeContent(
                savedMemes = savedMemes,
                onMemeClick = onSavedMemeClick,
                onCopyClick = onCopyClick,
            )
        }
        MyPagePullRefreshIndicator(
            isRefreshing = uiState.isRefreshing,
            pullRefreshState = pullRefreshState,
        )
    }
}

@Composable
private fun MyPageBody(
    modifier: Modifier = Modifier,
    levelUiModel: LevelUiModel,
    onSettingClick: () -> Unit,
) {
    Column(
        modifier = modifier.background(FarmemeTheme.backgroundColor.brandWhiteGradient),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        FarmemeActionToolBar(onClickActionIcon = onSettingClick)
        Spacer(modifier = Modifier.height(4.dp))
        MyPageSpeechBubble(modifier = Modifier.offset(y = 4.dp))
        Image(
            painter = painterResource(
                when (levelUiModel.myPageLevel) {
                    MyPageLevel.LEVEL1 -> R.drawable.img_character_level_1
                    MyPageLevel.LEVEL2 -> R.drawable.img_character_level_2
                    MyPageLevel.LEVEL3 -> R.drawable.img_character_level_3
                    MyPageLevel.LEVEL4 -> R.drawable.img_character_level_4
                }
            ),
            contentDescription = null,
        )
        Spacer(modifier = Modifier.height(30.dp))
        Text(
            text = levelUiModel.myPageLevel.title,
            color = FarmemeTheme.textColor.primary,
            style = FarmemeTheme.typography.highlight.normal,
        )
        Spacer(modifier = Modifier.height(30.dp))
        MyPageProgressBar(
            modifier = Modifier.padding(horizontal = 20.dp),
            levelUiModel = levelUiModel,
        )
        Spacer(modifier = Modifier.height(16.dp))
        MyPageLevelBox(
            modifier = Modifier.padding(horizontal = 20.dp),
            levelUiModel = levelUiModel,
        )
        Spacer(modifier = Modifier.height(40.dp))
    }
}

@Preview
@Composable
private fun MyPageScreenPreview() {
    MyPageScreen(
        uiState = MyPageUiState.INITIAL_STATE,
        onRefreshData = {},
        onSettingClick = {},
        onCopyClick = {},
        onRecentMemeClick = {},
        onSavedMemeClick = {},
    )
}