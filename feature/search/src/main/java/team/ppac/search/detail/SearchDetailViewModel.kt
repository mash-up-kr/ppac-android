package team.ppac.search.detail

import androidx.lifecycle.SavedStateHandle
import dagger.hilt.android.lifecycle.HiltViewModel
import team.ppac.common.android.base.BaseViewModel
import team.ppac.domain.usecase.GetSearchMemeUseCase
import team.ppac.search.detail.mvi.SearchDetailIntent
import team.ppac.search.detail.mvi.SearchDetailSideEffect
import team.ppac.search.detail.mvi.SearchDetailUiState
import javax.inject.Inject

@HiltViewModel
class SearchDetailViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val getSearchMemeUseCase: GetSearchMemeUseCase
) : BaseViewModel<SearchDetailUiState, SearchDetailSideEffect, SearchDetailIntent>(savedStateHandle) {

    override fun createInitialState(savedStateHandle: SavedStateHandle): SearchDetailUiState {
        val memeCategory = savedStateHandle.get<String>("memeCategory") ?: ""
        return SearchDetailUiState.INITIAL_STATE.copy(memeCategory = memeCategory)
    }

    override suspend fun handleIntent(intent: SearchDetailIntent) {

    }
}