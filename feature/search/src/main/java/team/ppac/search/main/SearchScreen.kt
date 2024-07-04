package team.ppac.search.main

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import team.ppac.designsystem.component.list.FarmemeListHeader
import team.ppac.designsystem.component.scaffold.FarmemeScaffold
import team.ppac.designsystem.component.tabbar.TabBarHeight
import team.ppac.designsystem.foundation.FarmemeIcon
import team.ppac.search.main.component.FarmemeSearchBar
import team.ppac.search.main.component.HotKeywordContent
import team.ppac.search.main.component.MemeCategoryContent
import team.ppac.search.main.mvi.SearchUiState

@OptIn(ExperimentalFoundationApi::class)
@Composable
internal fun SearchScreen(
    modifier: Modifier = Modifier,
    uiState: SearchUiState,
    onSearchBarClick: () -> Unit,
    onCategoryClick: (String) -> Unit,
) {
    FarmemeScaffold(
        modifier = modifier.fillMaxSize(),
        isIncludeHorizontalPadding = false,
        scaffoldState = rememberScaffoldState()
    ) {
        LazyColumn(
            contentPadding = PaddingValues(bottom = TabBarHeight)
        ) {
            stickyHeader {
                FarmemeSearchBar(
                    modifier = Modifier,
                    onSearchClick = onSearchBarClick
                )
            }
            item {
                FarmemeListHeader(
                    title = "두둥! 요즘 핫한 #키워드",
                    leadingIcon = { FarmemeIcon.Special(Modifier.size(20.dp)) }
                )
                HotKeywordContent(keywords = uiState.hotKeywords)
            }
            item { Spacer(modifier = Modifier.size(40.dp)) }
            item {
                FarmemeListHeader(
                    title = "무슨 밈 찾아?",
                    leadingIcon = { FarmemeIcon.Category(Modifier.size(20.dp)) }
                )
            }
            items(items = uiState.memeCategories) { memeCategory ->
                MemeCategoryContent(
                    uiModel = memeCategory,
                    onCategoryClick = onCategoryClick
                )
                Spacer(modifier = Modifier.size(20.dp))
            }
        }
    }
}

@Preview
@Composable
private fun MyPageScreenPreview() {
    SearchScreen(
        uiState = SearchUiState.INITIAL_STATE,
        onSearchBarClick = {},
        onCategoryClick = {}
    )
}