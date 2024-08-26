package team.ppac.search.detail

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import team.ppac.analytics.AnalyticsHelper
import team.ppac.analytics.type.ScreenType
import team.ppac.common.android.base.BaseComposable
import team.ppac.common.android.util.ComposableLifecycle
import team.ppac.search.detail.mvi.SearchDetailIntent
import team.ppac.search.detail.mvi.SearchDetailSideEffect

@Composable
internal fun SearchDetailRoute(
    modifier: Modifier = Modifier,
    analyticsHelper: AnalyticsHelper,
    viewModel: SearchDetailViewModel = hiltViewModel(),
    navigateBack: () -> Unit,
    navigateToMemeDetail: (String) -> Unit,
) {
    ComposableLifecycle { _, event ->
        when (event) {
            Lifecycle.Event.ON_START -> {
                analyticsHelper.reportScreen(ScreenType.SEARCH_DETAIL)
            }

            else -> {}
        }
    }

    BaseComposable(viewModel = viewModel) { uiState ->
        LaunchedEffect(key1 = viewModel) {
            viewModel.sideEffect.collect { sideEffect ->
                when (sideEffect) {
                    is SearchDetailSideEffect.NavigateToMemeDetail ->
                        navigateToMemeDetail(sideEffect.memeId)
                }
            }
        }

        SearchDetailScreen(
            modifier = modifier,
            uiState = uiState,
            handleLoadStates = viewModel::handleLoadErrorStates,
            onBackClick = navigateBack,
            onMemeClick = { viewModel.intent(SearchDetailIntent.ClickMeme(it)) },
            onRetryClick = { viewModel.intent(SearchDetailIntent.ClickErrorRetry) }
        )
    }
}