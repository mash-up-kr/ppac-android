package team.ppac.search.result

import androidx.lifecycle.SavedStateHandle
import androidx.paging.LoadState
import androidx.paging.LoadStates
import dagger.hilt.android.lifecycle.HiltViewModel
import team.ppac.common.android.base.BaseViewModel
import team.ppac.errorhandling.FarmemeNetworkException
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

    fun handleLoadErrorStates(loadStates: LoadStates) {
        val errorLoadState = arrayOf(
            loadStates.prepend,
            loadStates.append,
            loadStates.refresh
        ).filterIsInstance(LoadState.Error::class.java).firstOrNull()

        val exception = errorLoadState?.error
        if (exception != null) {
            when (exception) {
                is FarmemeNetworkException -> {
                    updateErrorState(isError = true)
                }
            }
        }
    }

    private fun updateErrorState(isError: Boolean) {
        reduce { copy(isError = isError) }
    }
}