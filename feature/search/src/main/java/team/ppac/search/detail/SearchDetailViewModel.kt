package team.ppac.search.detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import androidx.paging.map
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import team.ppac.common.android.base.BaseViewModel
import team.ppac.domain.usecase.GetSearchMemeUseCase
import team.ppac.search.detail.model.toSearchResultUiModel
import team.ppac.search.detail.mvi.SearchDetailIntent
import team.ppac.search.detail.mvi.SearchDetailSideEffect
import team.ppac.search.detail.mvi.SearchDetailUiState
import javax.inject.Inject

@HiltViewModel
class SearchDetailViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val getSearchMemeUseCase: GetSearchMemeUseCase,
) : BaseViewModel<SearchDetailUiState, SearchDetailSideEffect, SearchDetailIntent>(savedStateHandle) {

    init {
        val memeCategory = savedStateHandle.get<String>("memeCategory") ?: ""
        getSearchResults(memeCategory)
    }

    override fun createInitialState(savedStateHandle: SavedStateHandle): SearchDetailUiState {
        return SearchDetailUiState.INITIAL_STATE
    }

    override suspend fun handleIntent(intent: SearchDetailIntent) {

    }

    private fun getSearchResults(memeCategory: String) {
        viewModelScope.launch {
            val searchResults = getSearchMemeUseCase(memeCategory)
                .map { pagingData ->
                    pagingData.map { it.toSearchResultUiModel() }
                }

            reduce {
                copy(
                    memeCategory = memeCategory,
                    searchResults = searchResults
                )
            }
        }
    }
}