package team.ppac.search.main

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import team.ppac.analytics.AnalyticsHelper
import team.ppac.analytics.action.CATEGORY
import team.ppac.analytics.action.KEYWORD_NAME
import team.ppac.analytics.action.SearchAction
import team.ppac.analytics.type.ScreenType
import team.ppac.common.android.base.BaseComposable
import team.ppac.common.android.util.ComposableLifecycle
import team.ppac.search.main.component.OpenServiceDialog
import team.ppac.search.main.mvi.SearchIntent
import team.ppac.search.main.mvi.SearchSideEffect

@Composable
internal fun SearchRoute(
    modifier: Modifier = Modifier,
    analyticsHelper: AnalyticsHelper,
    viewModel: SearchViewModel = hiltViewModel(),
    navigateToKeywordCollection: (String) -> Unit,
    navigateToSearchResult: (String) -> Unit,
) {
    ComposableLifecycle { _, event ->
        when (event) {
            Lifecycle.Event.ON_START -> {
                analyticsHelper.reportScreen(ScreenType.SEARCH)
            }

            else -> {}
        }
    }

    BaseComposable(viewModel = viewModel) { uiState ->
        LaunchedEffect(key1 = viewModel) {
            viewModel.sideEffect.collect { sideEffect ->
                when (sideEffect) {
                    is SearchSideEffect.NavigateToKeywordCollection -> navigateToKeywordCollection(sideEffect.argument)
                    is SearchSideEffect.NavigateToSearchResult -> navigateToSearchResult(sideEffect.argument)
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
            onQueryChanged = viewModel::updateQuery,
            onInputDone = { query ->
                analyticsHelper.reportAction(SearchAction.CLICK_SEARCH_BAR, ScreenType.SEARCH)
                viewModel.intent(SearchIntent.InputDone(query))
            },
            onKeywordClick = { category, keyword ->
                analyticsHelper.reportAction(
                    action = SearchAction.CLICK_KEYWORD,
                    screen = ScreenType.SEARCH,
                    params = {
                        param(KEYWORD_NAME, keyword)
                        param(CATEGORY, category)
                    }
                )

                viewModel.intent(SearchIntent.ClickMemeKeyword(keyword))
            },
            onHotKeywordMemeClick = { keyword ->
                analyticsHelper.reportAction(
                    action = SearchAction.CLICK_HOT_KEYWORD,
                    screen = ScreenType.SEARCH,
                    params = {
                        param(KEYWORD_NAME, keyword)
                    }
                )

                viewModel.intent(SearchIntent.ClickKeywordCard(keyword))
            },
            onRetryClick = { viewModel.intent(SearchIntent.ClickErrorRetry) }
        )
    }
}