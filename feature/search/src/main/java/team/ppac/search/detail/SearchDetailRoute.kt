package team.ppac.search.detail

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import team.ppac.analytics.AnalyticsHelper
import team.ppac.analytics.action.SearchDetailAction
import team.ppac.analytics.type.ScreenType
import team.ppac.common.android.base.BaseComposable
import team.ppac.common.android.util.ComposableLifecycle
import team.ppac.search.detail.mvi.SearchDetailIntent
import team.ppac.search.detail.mvi.SearchDetailSideEffect
import timber.log.Timber

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
            onMemeClick = { memeId ->
                analyticsHelper.reportAction(
                    action = SearchDetailAction.CLICK_MEME,
                    screen = ScreenType.SEARCH_DETAIL,
                    params = {
                        param("keyword_name", uiState.keyword)
                    }
                )

                viewModel.intent(SearchDetailIntent.ClickMeme(memeId))
            },
            onRetryClick = { viewModel.intent(SearchDetailIntent.ClickErrorRetry) },
            onCopyClick = { memeId, memeTitle ->
                analyticsHelper.reportAction(
                    action = SearchDetailAction.CLICK_COPY,
                    screen = ScreenType.SEARCH_DETAIL,
                    params = {
                        param("meme_id", memeId)
                        param("meme_title", memeTitle)
                    }
                )
            },
            onScrollContent = {
                Timber.e("LOADING")
            }
        )
    }
}