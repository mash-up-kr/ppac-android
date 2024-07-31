package team.ppac.search.detail

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
import androidx.compose.ui.unit.dp
import androidx.paging.compose.collectAsLazyPagingItems
import team.ppac.designsystem.FarmemeTheme
import team.ppac.designsystem.component.scaffold.FarmemeScaffold
import team.ppac.designsystem.component.tabbar.TabBarHeight
import team.ppac.designsystem.component.toolbar.FarmemeBackToolBar
import team.ppac.search.detail.component.EmptyResultContent
import team.ppac.search.detail.component.SearchDetailResultContent
import team.ppac.search.detail.component.SearchDetailResultHeader
import team.ppac.search.detail.mvi.SearchDetailUiState

@Composable
internal fun SearchDetailScreen(
    modifier: Modifier = Modifier,
    uiState: SearchDetailUiState,
    onBackClick: () -> Unit,
    onMemeClick: (String) -> Unit,
    onCopyClick: () -> Unit,
) {
    val searchResults = uiState.searchResults.collectAsLazyPagingItems()

    FarmemeScaffold(
        modifier = modifier
            .fillMaxSize(),
        topBar = {
            FarmemeBackToolBar(
                title = uiState.memeCategory,
                onClickBackIcon = onBackClick
            )
            Divider(
                modifier = Modifier.fillMaxWidth(),
                color = FarmemeTheme.backgroundColor.assistive,
                thickness = 1.dp,
            )
        }
    ) { paddingValues ->
        val innerPadding = PaddingValues(
            top = paddingValues.calculateTopPadding(),
            start = paddingValues.calculateStartPadding(LayoutDirection.Ltr),
            end = paddingValues.calculateEndPadding(LayoutDirection.Ltr),
            bottom = paddingValues.calculateBottomPadding() + TabBarHeight
        )

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

@Preview
@Composable
private fun SearchDetailScreenPreview() {
    SearchDetailScreen(
        uiState = SearchDetailUiState.INITIAL_STATE,
        onBackClick = {},
        onMemeClick = {},
        onCopyClick = {}
    )
}