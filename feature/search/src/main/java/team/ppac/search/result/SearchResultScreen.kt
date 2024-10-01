package team.ppac.search.result

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.LayoutDirection
import androidx.paging.LoadStates
import team.ppac.common.android.component.error.FarmemeErrorScreen
import team.ppac.common.android.extension.collectPagingItemsWithHandleState
import team.ppac.designsystem.component.scaffold.FarmemeScaffold
import team.ppac.designsystem.component.tabbar.TabBarHeight
import team.ppac.designsystem.component.toolbar.FarmemeSearchToolbar
import team.ppac.search.detail.component.EmptyResultContent
import team.ppac.search.detail.component.SearchDetailLoadingContent
import team.ppac.search.detail.component.SearchDetailResultContent
import team.ppac.search.result.mvi.SearchResultUiState

@Composable
internal fun SearchResultScreen(
    modifier: Modifier = Modifier,
    uiState: SearchResultUiState,
    handleLoadStates: (LoadStates) -> Unit,
    onQueryChange: (String) -> Unit,
    onRetryClick: () -> Unit,
    onMemeClick: (String) -> Unit,
    onCopyClick: (String, String) -> Unit,
    onBackClick: () -> Unit,
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
                    FarmemeSearchToolbar(
                        text = uiState.query,
                        onTextChanged = onQueryChange,
                        onBackClick = onBackClick
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
                                SearchDetailResultContent(
                                    totalItemCount = uiState.totalMemeCount,
                                    searchResults = searchResults,
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