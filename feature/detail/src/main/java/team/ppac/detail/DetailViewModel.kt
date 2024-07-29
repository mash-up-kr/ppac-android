package team.ppac.detail

import androidx.compose.foundation.layout.size
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import team.ppac.common.android.base.BaseViewModel
import team.ppac.designsystem.foundation.FarmemeIcon
import team.ppac.detail.mapper.toDetailMemeUiModel
import team.ppac.detail.mvi.DetailIntent
import team.ppac.detail.mvi.DetailSideEffect
import team.ppac.detail.mvi.DetailUiState
import team.ppac.domain.usecase.DeleteSavedMemeUseCase
import team.ppac.domain.usecase.GetMemeUseCase
import team.ppac.domain.usecase.ReactMemeUseCase
import team.ppac.domain.usecase.SaveMemeUseCase
import team.ppac.errorhandling.FarmemeNetworkException
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val getMemeUseCase: GetMemeUseCase,
    private val saveMemeUseCase: SaveMemeUseCase,
    private val deleteSavedMemeUseCase: DeleteSavedMemeUseCase,
    private val reactMemeUseCase: ReactMemeUseCase,
) : BaseViewModel<DetailUiState, DetailSideEffect, DetailIntent>(savedStateHandle) {

    init {
        getMeme(currentState.memeId)
    }

    override fun createInitialState(savedStateHandle: SavedStateHandle): DetailUiState {
        val memeId = savedStateHandle["memeId"] ?: ""
        return DetailUiState.INITIAL_STATE.copy(memeId = memeId)
    }

    override fun handleClientException(throwable: Throwable) {
        if (throwable is FarmemeNetworkException) {
            reduce {
                copy(isError = true)
            }
        }
    }

    override suspend fun handleIntent(intent: DetailIntent) {
        when (intent) {
            is DetailIntent.ClickFunnyButton -> {
                incrementReactionCount()
                postSideEffect(DetailSideEffect.RunRisingEffect)
            }

            is DetailIntent.ClickBackButton -> {
                postSideEffect(DetailSideEffect.NavigateToBackEffect)
            }

            DetailIntent.ClickBottomButton.Copy -> {
                showSnackbar(
                    message = "이미지를 클립보드에 복사했어요",
                    icon = {
                        FarmemeIcon.CopyFilled(Modifier.size(20.dp))
                    }
                )
                postSideEffect(DetailSideEffect.CopyClipBoard)
            }

            is DetailIntent.ClickBottomButton.Share -> {
                postSideEffect(DetailSideEffect.ShareLink(intent.memeId))
            }

            is DetailIntent.ClickBottomButton.Farmeme -> {
                if (intent.isSavedMeme) {
                    deleteSavedMeme()
                    showSnackbar(message = "파밈을 취소했어요")
                } else {
                    saveMeme()
                    showSnackbar(
                        message = "파밈 완료!",
                        icon = {
                            FarmemeIcon.BookmarkFilled(Modifier.size(20.dp))
                        }
                    )
                }
            }

            DetailIntent.CLickRetryButton -> {
                getMeme(currentState.memeId)
            }
        }
    }

    private fun getMeme(memeId: String) {
        launch {
            val meme = getMemeUseCase(memeId)
            reduce {
                copy(
                    detailMemeUiModel = meme.toDetailMemeUiModel(),
                    isError = false,
                )
            }
        }
    }

    private fun saveMeme() {
        viewModelScope.launch {
            val isSaveSuccess = saveMemeUseCase(currentState.memeId)
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

    private fun deleteSavedMeme() {
        viewModelScope.launch {
            val isSaveSuccess = deleteSavedMemeUseCase(currentState.memeId)
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
        viewModelScope.launch {
            reactMemeUseCase(currentState.memeId)
            reduce {
                copy(
                    detailMemeUiModel = detailMemeUiModel.copy(
                        reactionCount = detailMemeUiModel.reactionCount + 1
                    )
                )
            }
        }
    }

    companion object {
        const val TAG = "DetailViewModel"
    }
}