package team.ppac.search.main

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import kotlinx.collections.immutable.toImmutableList
import team.ppac.common.android.base.BaseViewModel
import team.ppac.domain.usecase.GetRecommendKeywordsUseCase
import team.ppac.search.main.model.toRecommendKeywordUiModel
import team.ppac.search.main.mvi.SearchIntent
import team.ppac.search.main.mvi.SearchSideEffect
import team.ppac.search.main.mvi.SearchUiState
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val getRecommendKeywordsUseCase: GetRecommendKeywordsUseCase
) : BaseViewModel<SearchUiState, SearchSideEffect, SearchIntent>(savedStateHandle) {

    init {
        getRecommendKeywords()
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
                postSideEffect(SearchSideEffect.NavigateToSearchDetail(category = ""))
            }

            is SearchIntent.ClickMemeCategory -> {
                postSideEffect(SearchSideEffect.NavigateToSearchDetail(category = intent.category))
            }

            is SearchIntent.ClickServiceOpenDialogConfirm -> {
                showServiceOpenDialog(false)
            }

            is SearchIntent.ClickServiceOpenDialogDismiss -> {
                showServiceOpenDialog(false)
            }
        }
    }

    fun getRecommendKeywords() {
        viewModelScope.launch {
            val recommendKeywords = getRecommendKeywordsUseCase()
                .map { it.toRecommendKeywordUiModel() }.toImmutableList()
            reduce { copy(memeCategories = recommendKeywords) }
        }
    }

    private fun showServiceOpenDialog(showServiceOpenDialog: Boolean) {
        reduce {
            copy(showServiceOpenDialog = showServiceOpenDialog)
        }
    }
}