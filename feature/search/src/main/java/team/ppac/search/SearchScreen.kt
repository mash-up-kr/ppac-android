package team.ppac.search

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import team.ppac.designsystem.component.list.FarmemeListHeader
import team.ppac.designsystem.component.scaffold.FarmemeScaffold
import team.ppac.designsystem.component.tabbar.TabBarHeight
import team.ppac.designsystem.foundation.FarmemeIcon
import team.ppac.search.component.FarmemeSearchBar
import team.ppac.search.component.HotKeywordContent
import team.ppac.search.component.MemeCategoryContent
import team.ppac.search.component.OpenServiceDialog
import team.ppac.search.mvi.SearchIntent
import team.ppac.search.mvi.SearchSideEffect

@OptIn(ExperimentalFoundationApi::class)
@Composable
internal fun SearchScreen(
    modifier: Modifier = Modifier,
    viewModel: SearchViewModel = hiltViewModel(),
    onCategoryClick: () -> Unit,
    navigateToSearchDetail: () -> Unit,
) {
    val uiState by viewModel.state.collectAsStateWithLifecycle()

    LaunchedEffect(key1 = viewModel) {
        viewModel.sideEffect.collect { sideEffect ->
            when (sideEffect) {
                is SearchSideEffect.NavigateToSearchDetail -> navigateToSearchDetail()
            }
        }
    }

    if (uiState.showServiceOpenDialog) {
        OpenServiceDialog(
            onConfirmClick = { viewModel.showServiceOpenDialog(false) },
            onDismiss = { viewModel.showServiceOpenDialog(false) }
        )
    }

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
                    onSearchClick = { viewModel.intent(SearchIntent.ClickSearch(true)) }
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
        onCategoryClick = {},
        navigateToSearchDetail = {}
    )
}