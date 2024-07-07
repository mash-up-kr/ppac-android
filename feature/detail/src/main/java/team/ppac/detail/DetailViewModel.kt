package team.ppac.detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import team.ppac.common.android.base.BaseViewModel
import team.ppac.detail.mvi.DetailIntent
import team.ppac.detail.mvi.DetailSideEffect
import team.ppac.detail.mvi.DetailUiState
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
) : BaseViewModel<DetailUiState,DetailSideEffect,DetailIntent>(savedStateHandle) {

    init{
        viewModelScope.launch {
            //Todo getData using UseCase
        }
    }

    override fun createInitialState(savedStateHandle: SavedStateHandle): DetailUiState {
        return DetailUiState.INITIAL_STATE
    }

    override suspend fun handleIntent(intent: DetailIntent) {
        TODO("Not yet implemented")
    }
}