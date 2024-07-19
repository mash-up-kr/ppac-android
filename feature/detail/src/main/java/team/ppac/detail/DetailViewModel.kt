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
import team.ppac.domain.usecase.DeleteSavedMemeUseCase
import team.ppac.domain.usecase.GetMemeUseCase
import team.ppac.domain.usecase.SaveMemeUseCase
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val getMemeUseCase: GetMemeUseCase,
    private val saveMemeUseCase: SaveMemeUseCase,
    private val deleteSavedMemeUseCase: DeleteSavedMemeUseCase,
) : BaseViewModel<DetailUiState, DetailSideEffect, DetailIntent>(savedStateHandle) {

    init {
        getMeme(currentState.memeId)
    }

    override fun createInitialState(savedStateHandle: SavedStateHandle): DetailUiState {
        val memeId = savedStateHandle["memeId"] ?: ""
        return DetailUiState.INITIAL_STATE.copy(memeId = memeId)
    }

    override suspend fun handleIntent(intent: DetailIntent) {
        when (intent) {
            is DetailIntent.ClickFarmemeButton -> {
                if (intent.isSavedMeme) {
                    deleteSavedMeme(intent.memeId)
                } else {
                    saveMeme(intent.memeId)
                }
            }

            is DetailIntent.ClickFunnyButton -> {
                incrementReactionCount()
                postSideEffect(DetailSideEffect.RunRisingEffect)
            }
        }
    }

    private fun getMeme(memeId: String) {
        viewModelScope.launch {
            val meme = getMemeUseCase(memeId)
            reduce { copy(detailMemeUiModel = meme.toDetailMemeUiModel()) }
        }
    }

    private fun saveMeme(memeId: String) {
        viewModelScope.launch {
            val isSaveSuccess = saveMemeUseCase(memeId)
            if (isSaveSuccess) {
                reduce {
                    copy(
                        detailMemeUiModel = detailMemeUiModel
                            .copy(isSavedMeme = true)
                    )
                }
            }
        }
    }

    private fun deleteSavedMeme(memeId: String) {
        viewModelScope.launch {
            val isSaveSuccess = deleteSavedMemeUseCase(memeId)
            if (isSaveSuccess) {
                reduce {
                    copy(
                        detailMemeUiModel = detailMemeUiModel
                            .copy(isSavedMeme = false)
                    )
                }
            }
        }
    }

    private fun incrementReactionCount() {
        reduce {
            copy(
                detailMemeUiModel = detailMemeUiModel.copy(
                    reactionCount = detailMemeUiModel.reactionCount + 1
                )
            )
        }
    }

    companion object {
        const val TAG = "DetailViewModel"
    }
}