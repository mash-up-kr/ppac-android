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
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val getMemeUseCase: GetMemeUseCase,
) : BaseViewModel<DetailUiState, DetailSideEffect, DetailIntent>(savedStateHandle) {

    init {
        getMeme(currentState.memeId)
    }

    override fun createInitialState(savedStateHandle: SavedStateHandle): DetailUiState {
        val memeId = savedStateHandle["memeId"] ?: ""
        Timber.tag(TAG).d("memeId = $memeId")
        return DetailUiState.INITIAL_STATE.copy(memeId = memeId)
    }

    override suspend fun handleIntent(intent: DetailIntent) {
        TODO("Not yet implemented")
    }

    private fun getMeme(memeId: String) {
        viewModelScope.launch {
            val meme = getMemeUseCase(memeId)
            reduce { copy(detailMemeUiModel = meme.toDetailMemeUiModel()) }
        }
    }

    companion object {
        const val TAG = "DetailViewModel"
    }
}