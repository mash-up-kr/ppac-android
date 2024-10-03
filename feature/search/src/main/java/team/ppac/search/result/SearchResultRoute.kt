package team.ppac.search.result

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import team.ppac.common.android.base.BaseComposable
import team.ppac.search.result.mvi.ClickBackButton
import team.ppac.search.result.mvi.ClickErrorRetry
import team.ppac.search.result.mvi.ClickMeme
import team.ppac.search.result.mvi.NavigateBack
import team.ppac.search.result.mvi.NavigateToMemeDetail

@Composable
internal fun SearchResultRoute(
    modifier: Modifier = Modifier,
    viewModel: SearchResultViewModel = hiltViewModel(),
    navigateBack: () -> Unit,
    navigateToMemeDetail: (String) -> Unit,
) {
    BaseComposable(viewModel = viewModel) { uiState ->
        LaunchedEffect(key1 = viewModel) {
            viewModel.sideEffect.collect { sideEffect ->
                when (sideEffect) {
                    is NavigateBack -> navigateBack()
                    is NavigateToMemeDetail -> navigateToMemeDetail(sideEffect.memeId)
                }
            }
        }

        SearchResultScreen(
            modifier = modifier,
            uiState = uiState,
            onQueryChange = viewModel::updateSearchQuery,
            onInputDone = viewModel::getSearchResults,
            handleLoadStates = viewModel::handleLoadErrorStates,
            onRetryClick = { viewModel.intent(ClickErrorRetry) },
            onMemeClick = { viewModel.intent(ClickMeme(it)) },
            onCopyClick = { _, _ -> },
            onBackClick = { viewModel.intent(ClickBackButton) }
        )
    }
}