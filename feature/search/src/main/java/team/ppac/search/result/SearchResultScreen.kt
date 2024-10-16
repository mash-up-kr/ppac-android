package team.ppac.search.result

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.LayoutDirection
import androidx.paging.LoadStates
import team.ppac.common.android.component.empty.EmptyResultContent
import team.ppac.common.android.component.error.FarmemeErrorScreen
import team.ppac.common.android.component.paging.PagingItemsLoadingContent
import team.ppac.common.android.component.paging.PagingMemesContent
import team.ppac.common.android.extension.collectPagingItemsWithHandleState
import team.ppac.designsystem.component.scaffold.FarmemeScaffold
import team.ppac.designsystem.component.tabbar.TabBarHeight
import team.ppac.designsystem.component.toolbar.FarmemeSearchToolbar
import team.ppac.search.result.mvi.SearchResultUiState

@Composable
internal fun SearchResultScreen(
    modifier: Modifier = Modifier,
    uiState: SearchResultUiState,
    handleLoadStates: (LoadStates) -> Unit,
    onQueryChange: (String) -> Unit,
    onInputDone: (String) -> Unit,
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
                        onBackClick = onBackClick,
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Text,
                            imeAction = ImeAction.Done
                        ),
                        keyboardActions = KeyboardActions(
                            onDone = { onInputDone(uiState.query) }
                        ),
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
                                    pagingItems = searchResults,
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