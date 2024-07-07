package team.ppac.detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import team.ppac.common.android.base.BaseViewModel
import team.ppac.detail.mapper.toDetailMemeUiModel
import team.ppac.detail.mvi.DetailIntent
import team.ppac.detail.mvi.DetailSideEffect
import team.ppac.detail.mvi.DetailUiState
import team.ppac.domain.usecase.GetMemeUseCase
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val getMemeUseCase: GetMemeUseCase,
) : BaseViewModel<DetailUiState, DetailSideEffect, DetailIntent>(savedStateHandle) {

    init {
        getMeme()
    }

    private fun getMeme() {
        viewModelScope.launch {
            val meme = getMemeUseCase("668a44950289555e368174a6") //Todo memeId navigate할때 전달 로직 필요
            reduce { copy(detailMemeUiModel = meme.toDetailMemeUiModel()) }
        }
    }

    override fun createInitialState(savedStateHandle: SavedStateHandle): DetailUiState {
        return DetailUiState.INITIAL_STATE
    }

    override suspend fun handleIntent(intent: DetailIntent) {
        TODO("Not yet implemented")
    }
}