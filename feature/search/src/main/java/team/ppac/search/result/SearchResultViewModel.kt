package team.ppac.search.result

import androidx.lifecycle.SavedStateHandle
import dagger.hilt.android.lifecycle.HiltViewModel
import team.ppac.common.android.base.BaseViewModel
import team.ppac.search.result.mvi.SearchResultIntent
import team.ppac.search.result.mvi.SearchResultSideEffect
import team.ppac.search.result.mvi.SearchResultUiState
import javax.inject.Inject

@HiltViewModel
class SearchResultViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
) : BaseViewModel<SearchResultUiState, SearchResultSideEffect, SearchResultIntent>(savedStateHandle) {

    override fun createInitialState(savedStateHandle: SavedStateHandle): SearchResultUiState {
        return SearchResultUiState.INITIAL_STATE
    }

    override suspend fun handleIntent(intent: SearchResultIntent) {
        TODO("Not yet implemented")
    }

    override fun handleClientException(throwable: Throwable) {
        TODO("Not yet implemented")
    }

    fun updateSearchQuery(query: String) {
        reduce { copy(query = query) }
    }
}