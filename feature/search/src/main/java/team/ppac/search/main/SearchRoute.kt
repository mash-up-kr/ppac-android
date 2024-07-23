package team.ppac.search.main

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import team.ppac.common.android.base.BaseComposable
import team.ppac.search.main.component.OpenServiceDialog
import team.ppac.search.main.mvi.SearchIntent
import team.ppac.search.main.mvi.SearchSideEffect

@Composable
internal fun SearchRoute(
    modifier: Modifier = Modifier,
    viewModel: SearchViewModel = hiltViewModel(),
    navigateToSearchDetail: (String) -> Unit,
) {
    BaseComposable(viewModel = viewModel) { uiState ->
        LaunchedEffect(key1 = viewModel) {
            viewModel.sideEffect.collect { sideEffect ->
                when (sideEffect) {
                    is SearchSideEffect.NavigateToSearchDetail -> navigateToSearchDetail(sideEffect.argument)
                }
            }
        }

        if (uiState.showServiceOpenDialog) {
            OpenServiceDialog(
                onConfirmClick = { viewModel.intent(SearchIntent.ClickServiceOpenDialogConfirm) },
                onDismiss = { viewModel.intent(SearchIntent.ClickServiceOpenDialogDismiss) }
            )
        }

        SearchScreen(
            modifier = modifier,
            uiState = uiState,
            onSearchBarClick = { viewModel.intent(SearchIntent.ClickSearch) },
            onCategoryClick = { viewModel.intent(SearchIntent.ClickMemeCategory(category = it)) },
            onHotKeywordMemeClick = { viewModel.intent(SearchIntent.ClickKeywordCard(keyword = it)) }
        )
    }
}