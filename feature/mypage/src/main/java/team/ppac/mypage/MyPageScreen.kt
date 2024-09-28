package team.ppac.mypage

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.paging.compose.collectAsLazyPagingItems
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import team.ppac.common.android.component.error.FarmemeErrorScreen
import team.ppac.common.android.util.showSkeleton
import team.ppac.common.android.util.visibility
import team.ppac.designsystem.FarmemeTheme
import team.ppac.designsystem.R
import team.ppac.designsystem.component.scaffold.FarmemeScaffold
import team.ppac.designsystem.component.scaffold.type.BackgroundColorType
import team.ppac.designsystem.component.tabbar.TabBarHeight
import team.ppac.designsystem.component.toolbar.FarmemeActionToolBar
import team.ppac.designsystem.foundation.FarmemeRadius
import team.ppac.domain.repository.SavedMemeEvent
import team.ppac.mypage.component.MyPageLevelBox
import team.ppac.mypage.component.MyPageMemesTabBar
import team.ppac.mypage.component.MyPageProgressBar
import team.ppac.mypage.component.MyPagePullRefreshIndicator
import team.ppac.mypage.component.MyPageSpeechBubble
import team.ppac.mypage.component.RecentMemeContent
import team.ppac.mypage.component.SavedMemeContent
import team.ppac.mypage.model.LevelUiModel
import team.ppac.mypage.model.MyPageLevel
import team.ppac.mypage.mvi.MyPageIntent
import team.ppac.mypage.mvi.MyPageTabType
import team.ppac.mypage.mvi.MyPageUiState

@OptIn(ExperimentalMaterialApi::class)
@Composable
internal fun MyPageScreen(
    uiState: MyPageUiState,
    savedMemeEventFlow: Flow<SavedMemeEvent>,
    onIntent: (MyPageIntent) -> Unit,
) {
    val savedMemes = uiState.savedMemes.collectAsLazyPagingItems()
    val pullRefreshState = rememberPullRefreshState(
        refreshing = uiState.isRefreshing,
        onRefresh = {
            onIntent(MyPageIntent.RefreshData)
            savedMemes.refresh()
        },
    )

    LaunchedEffect(key1 = Unit) {
        savedMemeEventFlow.collect { event ->
            when (event) {
                SavedMemeEvent.Refresh -> savedMemes.refresh()
            }
        }
    }

    FarmemeScaffold(
        modifier = Modifier
            .fillMaxSize()
            .pullRefresh(pullRefreshState),
        backgroundColorType = BackgroundColorType.SolidColor(FarmemeTheme.backgroundColor.white),
    ) {
        Crossfade(
            targetState = uiState.isError,
            label = "",
        ) { isError ->
            if (isError) {
                FarmemeErrorScreen(
                    modifier = Modifier
                        .fillMaxSize()
                        .systemBarsPadding()
                        .padding(bottom = TabBarHeight),
                    title = "정보를 불러오지 못 했어요.\n 새로고침 해주세요.",
                    onRetryClick = {
                        onIntent(MyPageIntent.ClickRetryButton)
                    },
                )
            } else {
                Column(
                    modifier = Modifier
                        .navigationBarsPadding()
                        .padding(bottom = TabBarHeight)
                        .verticalScroll(rememberScrollState()),
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    MyPageBody(
                        levelUiModel = uiState.levelUiModel,
                        isLoading = uiState.isLoading,
                        onSettingClick = {
                            onIntent(MyPageIntent.ClickSettingButton)
                        },
                    )
                    Spacer(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(10.dp)
                            .background(FarmemeTheme.skeletonColor.primary),
                    )
                    RecentMemeContent(
                        recentMemes = uiState.recentMemes,
                        onMemeClick = { memeId ->
                            onIntent(MyPageIntent.ClickRecentMemeItem(memeId = memeId))
                        },
                        isLoading = uiState.isLoading,
                    )
                    MyPageMemesTabBar(
                        currentTabType = uiState.currentTabType,
                        onClick = { tab ->
                            onIntent(MyPageIntent.ClickMemesTab(currentTabType = tab))
                        },
                    )
                    Spacer(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(1.dp)
                            .background(FarmemeTheme.borderColor.tertiary),
                    )
                    if (!uiState.isLoading) {
                        when (uiState.currentTabType) {
                            MyPageTabType.MY_MEMES -> {
                                // TODO(ze-zeh) : 나의 밈
                            }

                            MyPageTabType.SAVED_MEMES -> {
                                SavedMemeContent(
                                    savedMemes = savedMemes,
                                    onMemeClick = { memeId ->
                                        onIntent(MyPageIntent.ClickSavedMemeItem(memeId = memeId))
                                    },
                                    onCopyClick = {
                                        onIntent(MyPageIntent.ClickCopy(it))
                                    },
                                )
                            }
                        }
                    }
                }
                MyPagePullRefreshIndicator(
                    isRefreshing = uiState.isRefreshing,
                    pullRefreshState = pullRefreshState,
                )
            }
        }
    }
}

@Composable
private fun MyPageBody(
    modifier: Modifier = Modifier,
    levelUiModel: LevelUiModel,
    isLoading: Boolean,
    onSettingClick: () -> Unit,
) {
    Column(
        modifier = modifier.background(FarmemeTheme.backgroundColor.brandWhiteGradient),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        FarmemeActionToolBar(
            onClickActionIcon = {
                if (!isLoading) {
                    onSettingClick()
                }
            }
        )
        Spacer(modifier = Modifier.height(4.dp))
        MyPageSpeechBubble(
            modifier = Modifier
                .visibility(isVisible = !isLoading)
                .offset(y = 4.dp),
        )
        Image(
            modifier = Modifier.visibility(isVisible = !isLoading),
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
        if (isLoading) {
            Box(
                modifier = Modifier
                    .size(width = 160.dp, height = 24.dp)
                    .clip(shape = FarmemeRadius.Radius4.shape)
                    .showSkeleton(isLoading = isLoading)
            )
        } else {
            Text(
                text = levelUiModel.myPageLevel.title,
                color = FarmemeTheme.textColor.primary,
                style = FarmemeTheme.typography.highlight.normal,
            )
        }
        Spacer(modifier = Modifier.height(30.dp))
        MyPageProgressBar(
            modifier = Modifier.padding(horizontal = 20.dp),
            levelUiModel = levelUiModel,
            isLoading = isLoading,
        )
        Spacer(modifier = Modifier.height(16.dp))
        MyPageLevelBox(
            modifier = Modifier.padding(horizontal = 20.dp),
            levelUiModel = levelUiModel,
            isLoading = isLoading,
        )
        Spacer(modifier = Modifier.height(40.dp))
    }
}

@Preview
@Composable
private fun MyPageScreenPreview() {
    MyPageScreen(
        uiState = MyPageUiState.INITIAL_STATE,
        onIntent = {},
        savedMemeEventFlow = flowOf(),
    )
}