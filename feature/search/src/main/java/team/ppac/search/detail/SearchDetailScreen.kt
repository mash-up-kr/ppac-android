package team.ppac.search.detail

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.LayoutDirection
import androidx.paging.LoadStates
import team.ppac.common.android.component.error.FarmemeErrorScreen
import team.ppac.common.android.extension.collectPagingItemsWithHandleState
import team.ppac.designsystem.component.scaffold.FarmemeScaffold
import team.ppac.designsystem.component.tabbar.TabBarHeight
import team.ppac.designsystem.component.toolbar.FarmemeBackToolBar
import team.ppac.search.detail.component.EmptyResultContent
import team.ppac.search.detail.component.SearchDetailLoadingContent
import team.ppac.search.detail.component.SearchDetailResultContent
import team.ppac.search.detail.component.SearchDetailResultHeader
import team.ppac.search.detail.mvi.SearchDetailUiState

@Composable
internal fun SearchDetailScreen(
    modifier: Modifier = Modifier,
    uiState: SearchDetailUiState,
    handleLoadStates: (LoadStates) -> Unit,
    onBackClick: () -> Unit,
    onMemeClick: (String) -> Unit,
    onCopyClick: () -> Unit,
    onRetryClick: () -> Unit,
) {
    val searchResults = uiState.searchResults.collectPagingItemsWithHandleState(handleLoadStates)

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
                    FarmemeBackToolBar(
                        title = uiState.memeCategory,
                        onClickBackIcon = onBackClick
                    )
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
                        SearchDetailLoadingContent(
                            modifier = Modifier.fillMaxSize(),
                            uiState = uiState
                        )
                    }

                    else -> {
                        Column(
                            modifier = Modifier.padding(innerPadding)
                        ) {
                            if (searchResults.itemCount == 0) {
                                EmptyResultContent()
                            }
                            SearchDetailResultHeader(totalCount = searchResults.itemCount)
                            SearchDetailResultContent(
                                searchResults = searchResults,
                                onMemeClick = onMemeClick,
                                onCopyClick = onCopyClick
                            )
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
    SearchDetailScreen(
        uiState = SearchDetailUiState.INITIAL_STATE,
        handleLoadStates = {},
        onBackClick = {},
        onMemeClick = {},
        onCopyClick = {},
        onRetryClick = {}
    )
}