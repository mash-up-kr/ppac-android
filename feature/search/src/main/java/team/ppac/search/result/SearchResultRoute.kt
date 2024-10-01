package team.ppac.search.result

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import team.ppac.common.android.base.BaseComposable

@Composable
internal fun SearchResultRoute(
    modifier: Modifier = Modifier,
    viewModel: SearchResultViewModel = hiltViewModel(),
) {
    BaseComposable(viewModel = viewModel) { uiState ->
        SearchResultScreen(
            modifier = modifier,
            uiState = uiState,
            onQueryChange = viewModel::updateSearchQuery,
            handleLoadStates = viewModel::handleLoadErrorStates,
            onRetryClick = {},
            onMemeClick = {},
            onCopyClick = { _, _ ->},
        )
    }
}