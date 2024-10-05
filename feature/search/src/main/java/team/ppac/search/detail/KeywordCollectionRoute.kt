package team.ppac.search.detail

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import kotlinx.coroutines.flow.collectLatest
import team.ppac.analytics.AnalyticsHelper
import team.ppac.analytics.action.KEYWORD_NAME
import team.ppac.analytics.action.MEME_ID
import team.ppac.analytics.action.MEME_TITLE
import team.ppac.analytics.action.PAGINATION_COUNT
import team.ppac.analytics.action.SearchDetailAction
import team.ppac.analytics.type.ScreenType
import team.ppac.common.android.base.BaseComposable
import team.ppac.common.android.util.ComposableLifecycle
import team.ppac.search.detail.mvi.KeywordCollectionIntent
import team.ppac.search.detail.mvi.KeywordCollectionSideEffect

@Composable
internal fun KeywordCollectionRoute(
    modifier: Modifier = Modifier,
    analyticsHelper: AnalyticsHelper,
    viewModel: KeywordCollectionViewModel = hiltViewModel(),
    navigateBack: () -> Unit,
    navigateToMemeDetail: (String) -> Unit,
) {
    var lastPage by remember { mutableIntStateOf(0) }

    ComposableLifecycle { _, event ->
        when (event) {
            Lifecycle.Event.ON_START -> {
                analyticsHelper.reportScreen(ScreenType.SEARCH_DETAIL)
            }

            else -> {}
        }
    }

    BaseComposable(viewModel = viewModel) { uiState ->
        LaunchedEffect(Unit) {
            viewModel.currentPage.collectLatest { currentPage ->
                lastPage = currentPage
            }
        }

        LaunchedEffect(key1 = viewModel) {
            viewModel.sideEffect.collect { sideEffect ->
                when (sideEffect) {
                    is KeywordCollectionSideEffect.NavigateToMemeDetail ->
                        navigateToMemeDetail(sideEffect.memeId)
                }
            }
        }

        KeywordCollectionScreen(
            modifier = modifier,
            uiState = uiState,
            handleLoadStates = viewModel::handleLoadErrorStates,
            onBackClick = {
                analyticsHelper.reportAction(
                    action = SearchDetailAction.SCROLL,
                    screen = ScreenType.SEARCH_DETAIL,
                    params = {
                        param(KEYWORD_NAME, uiState.keyword)
                        param(PAGINATION_COUNT, "$lastPage")
                    }
                )
                navigateBack()
            },
            onMemeClick = { memeId ->
                analyticsHelper.reportAction(
                    action = SearchDetailAction.CLICK_MEME,
                    screen = ScreenType.SEARCH_DETAIL,
                    params = {
                        param(KEYWORD_NAME, uiState.keyword)
                    }
                )

                viewModel.intent(KeywordCollectionIntent.ClickMeme(memeId))
            },
            onRetryClick = { viewModel.intent(KeywordCollectionIntent.ClickErrorRetry) },
            onCopyClick = { memeId, memeTitle ->
                analyticsHelper.reportAction(
                    action = SearchDetailAction.CLICK_COPY,
                    screen = ScreenType.SEARCH_DETAIL,
                    params = {
                        param(MEME_ID, memeId)
                        param(MEME_TITLE, memeTitle)
                    }
                )
            },
        )
    }
}