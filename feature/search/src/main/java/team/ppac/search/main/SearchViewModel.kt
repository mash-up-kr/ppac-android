package team.ppac.search.main

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.toImmutableList
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import team.ppac.common.android.base.BaseViewModel
import team.ppac.domain.usecase.GetRecommendKeywordsUseCase
import team.ppac.domain.usecase.GetTopKeywordsUseCase
import team.ppac.search.main.model.HotKeywordUiModel
import team.ppac.search.main.model.RecommendKeywordUiModel
import team.ppac.search.main.model.toHotKeywordUiModel
import team.ppac.search.main.model.toRecommendKeywordUiModel
import team.ppac.search.main.mvi.SearchIntent
import team.ppac.search.main.mvi.SearchSideEffect
import team.ppac.search.main.mvi.SearchUiState
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val getRecommendKeywordsUseCase: GetRecommendKeywordsUseCase,
    private val getTopKeywordsUseCase: GetTopKeywordsUseCase,
) : BaseViewModel<SearchUiState, SearchSideEffect, SearchIntent>(savedStateHandle) {

    init {
        getSearchScreenUiContent()
    }

    override fun createInitialState(savedStateHandle: SavedStateHandle): SearchUiState {
        return SearchUiState.INITIAL_STATE
    }

    override suspend fun handleIntent(intent: SearchIntent) {
        when (intent) {
            is SearchIntent.ClickSearch -> {
                showServiceOpenDialog(true)
            }

            is SearchIntent.ClickKeywordCard -> {
                postSideEffect(SearchSideEffect.NavigateToSearchDetail(argument = intent.keyword))
            }

            is SearchIntent.ClickMemeCategory -> {
                postSideEffect(SearchSideEffect.NavigateToSearchDetail(argument = intent.category))
            }

            is SearchIntent.ClickServiceOpenDialogConfirm -> {
                showServiceOpenDialog(false)
            }

            is SearchIntent.ClickServiceOpenDialogDismiss -> {
                showServiceOpenDialog(false)
            }
        }
    }

    private fun getSearchScreenUiContent() {
        viewModelScope.launch {
            val topKeywordsDeferredTask = async { getTopKeywordsUseCase() }
            val recommendKeywordsDeferredTask = async { getRecommendKeywordsUseCase() }

            val topKeywords = topKeywordsDeferredTask.await()
                .map { it.toHotKeywordUiModel() }
                .toImmutableList()
            val recommendKeywords = recommendKeywordsDeferredTask.await()
                .map { it.toRecommendKeywordUiModel() }
                .toImmutableList()

            updateSearchUiContent(topKeywords, recommendKeywords)
        }
    }

    private fun updateSearchUiContent(
        hotKeywords: ImmutableList<HotKeywordUiModel>,
        recommendKeywords: ImmutableList<RecommendKeywordUiModel>,
    ) {
        reduce {
            copy(
                hotKeywords = hotKeywords,
                memeCategories = recommendKeywords
            )
        }
    }

    private fun updateMemeCategories(recommendKeywords: ImmutableList<RecommendKeywordUiModel>) {
        reduce { copy(memeCategories = recommendKeywords) }
    }

    private fun showServiceOpenDialog(showServiceOpenDialog: Boolean) {
        reduce {
            copy(showServiceOpenDialog = showServiceOpenDialog)
        }
    }
}