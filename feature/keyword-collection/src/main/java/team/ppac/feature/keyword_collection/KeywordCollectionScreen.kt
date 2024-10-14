package team.ppac.feature.keyword_collection

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.LayoutDirection
import androidx.paging.LoadStates
import team.ppac.common.android.component.empty.EmptyResultContent
import team.ppac.common.android.component.paging.PagingMemesContent
import team.ppac.common.android.component.error.FarmemeErrorScreen
import team.ppac.common.android.extension.collectPagingItemsWithHandleState
import team.ppac.designsystem.FarmemeTheme
import team.ppac.designsystem.component.scaffold.FarmemeScaffold
import team.ppac.designsystem.component.tabbar.TabBarHeight
import team.ppac.designsystem.component.toolbar.FarmemeBackToolBar
import team.ppac.common.android.component.paging.PagingItemsLoadingContent
import team.ppac.feature.keyword_collection.mvi.KeywordCollectionUiState

@Composable
internal fun KeywordCollectionScreen(
    modifier: Modifier = Modifier,
    uiState: KeywordCollectionUiState,
    handleLoadStates: (LoadStates) -> Unit,
    onBackClick: () -> Unit,
    onMemeClick: (String) -> Unit,
    onRetryClick: () -> Unit,
    onCopyClick: (String, String) -> Unit,
) {
    val memes = uiState.memes.collectPagingItemsWithHandleState(handleLoadStates)

    when {
        uiState.isError -> {
            FarmemeErrorScreen(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(bottom = TabBarHeight),
                title = "정보를 불러오지 못 했어요.\n새로고침 해주세요.",
                onRetryClick = onRetryClick
            )
        }

        else -> {
            FarmemeScaffold(
                modifier = modifier.fillMaxSize(),
                topBar = {
                    Column {
                        FarmemeBackToolBar(
                            title = uiState.keyword,
                            onBackIconClick = onBackClick
                        )
                        Divider(
                            modifier = Modifier.fillMaxWidth(),
                            color = FarmemeTheme.backgroundColor.assistive
                        )
                    }
                }
            ) { paddingValues ->
                val innerPadding = PaddingValues(
                    top = paddingValues.calculateTopPadding(),
                    start = paddingValues.calculateStartPadding(LayoutDirection.Ltr),
                    end = paddingValues.calculateEndPadding(LayoutDirection.Ltr),
                    bottom = paddingValues.calculateBottomPadding() + TabBarHeight
                )

                when {
                    uiState.isLoading -> {
                        PagingItemsLoadingContent(
                            modifier = Modifier.fillMaxSize(),
                            isLoading = uiState.isLoading
                        )
                    }

                    else -> {
                        Column(
                            modifier = Modifier.padding(innerPadding)
                        ) {
                            if (uiState.totalMemeCount == 0) {
                                EmptyResultContent()
                            } else {
                                PagingMemesContent(
                                    totalItemCount = uiState.totalMemeCount,
                                    pagingItems = memes,
                                    onMemeClick = onMemeClick,
                                    onCopyClick = onCopyClick,
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

@Preview
@Composable
private fun SearchDetailScreenPreview() {
    KeywordCollectionScreen(
        uiState = KeywordCollectionUiState.INITIAL_STATE,
        handleLoadStates = {},
        onBackClick = {},
        onMemeClick = {},
        onRetryClick = {},
        onCopyClick = { _, _ -> },
    )
}