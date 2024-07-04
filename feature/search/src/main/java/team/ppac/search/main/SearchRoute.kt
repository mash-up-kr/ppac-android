package team.ppac.search.main

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import team.ppac.search.main.component.OpenServiceDialog
import team.ppac.search.main.mvi.SearchIntent
import team.ppac.search.main.mvi.SearchSideEffect

@Composable
internal fun SearchRoute(
    modifier: Modifier = Modifier,
    viewModel: SearchViewModel = hiltViewModel(),
    navigateToSearchDetail: (String) -> Unit,
) {
    val uiState by viewModel.state.collectAsStateWithLifecycle()

    LaunchedEffect(key1 = viewModel) {
        viewModel.sideEffect.collect { sideEffect ->
            when (sideEffect) {
                is SearchSideEffect.NavigateToSearchDetail -> navigateToSearchDetail(sideEffect.category)
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
        onCategoryClick = { viewModel.intent(SearchIntent.ClickMemeCategory(category = it)) }
    )
}