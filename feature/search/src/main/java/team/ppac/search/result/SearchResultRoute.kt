package team.ppac.search.result

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import team.ppac.common.android.base.BaseComposable
import team.ppac.search.result.mvi.ClickBackButton
import team.ppac.search.result.mvi.NavigateBack
import timber.log.Timber

@Composable
internal fun SearchResultRoute(
    modifier: Modifier = Modifier,
    viewModel: SearchResultViewModel = hiltViewModel(),
    navigateBack: () -> Unit,
) {
    BaseComposable(viewModel = viewModel) { uiState ->
        Timber.e("$uiState")
        LaunchedEffect(key1 = viewModel) {
            viewModel.sideEffect.collect { sideEffect ->
                when (sideEffect) {
                    NavigateBack -> navigateBack()
                }
            }
        }

        SearchResultScreen(
            modifier = modifier,
            uiState = uiState,
            onQueryChange = viewModel::updateSearchQuery,
            onInputDone = viewModel::getSearchResults,
            handleLoadStates = viewModel::handleLoadErrorStates,
            onRetryClick = {},
            onMemeClick = {},
            onCopyClick = { _, _ -> },
            onBackClick = { viewModel.intent(ClickBackButton) }
        )
    }
}