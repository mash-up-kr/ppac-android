package team.ppac.mypage

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
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
import team.ppac.mypage.mvi.MyPageIntent
import team.ppac.mypage.mvi.MyPageSideEffect

@OptIn(ExperimentalMaterialApi::class)
@Composable
internal fun MyPageScreen(
    viewModel: MyPageViewModel = hiltViewModel(),
    navigateToDetail: (String) -> Unit,
    navigateToSetting: () -> Unit,
) {
    val state by viewModel.state.collectAsState()
    val levelUiModel = state.levelUiModel
    val recentMemes = state.recentMemes
    val savedMemes = state.savedMemes.collectAsLazyPagingItems()
    val pullRefreshState = rememberPullRefreshState(
        refreshing = state.isLoading,
        onRefresh = { viewModel.intent(MyPageIntent.RefreshData) },
    )

    LaunchedEffect(key1 = viewModel) {
        viewModel.sideEffect.collect { sideEffect ->
            when (sideEffect) {
                is MyPageSideEffect.NavigateToDetail -> navigateToDetail(sideEffect.memeId)
                is MyPageSideEffect.NavigateToSetting -> navigateToSetting()
            }
        }
    }

    FarmemeScaffold(
        modifier = Modifier
            .fillMaxSize()
            .pullRefresh(pullRefreshState),
        backgroundColorType = BackgroundColorType.SolidColor(FarmemeTheme.backgroundColor.white),
    ) {
        LazyColumn(
            horizontalAlignment = Alignment.CenterHorizontally,
            contentPadding = PaddingValues(bottom = TabBarHeight),
        ) {
            item {
                MyPageBody(
                    levelUiModel = levelUiModel,
                    onClickToolBarActionIcon = { viewModel.intent(MyPageIntent.ClickSettingButton) },
                )
            }
            item {
                Spacer(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(10.dp)
                        .background(FarmemeTheme.skeletonColor.primary),
                )
            }
            item {
                RecentMemeContent(
                    recentMemes = recentMemes,
                    onClickMemeItem = { memeId ->
                        viewModel.intent(MyPageIntent.ClickRecentMemeItem(memeId = memeId))
                    },
                )
            }
            item {
                SavedMemeContent(
                    savedMemes = savedMemes,
                    onMemeItemClick = { memeId ->
                        viewModel.intent(MyPageIntent.ClickSavedMemeItem(memeId = memeId))
                    },
                )
            }
        }
        MyPagePullRefreshIndicator(
            isLoading = state.isLoading,
            pullRefreshState = pullRefreshState,
        )
    }
}

@Composable
private fun MyPageBody(
    modifier: Modifier = Modifier,
    levelUiModel: LevelUiModel,
    onClickToolBarActionIcon: () -> Unit,
) {
    Column(
        modifier = modifier.background(FarmemeTheme.backgroundColor.brandWhiteGradient),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        FarmemeActionToolBar(onClickActionIcon = onClickToolBarActionIcon)
        Spacer(modifier = Modifier.height(8.dp))
        MyPageSpeechBubble()
        Spacer(modifier = Modifier.height(5.dp))
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
        navigateToDetail = {},
        navigateToSetting = {},
    )
}