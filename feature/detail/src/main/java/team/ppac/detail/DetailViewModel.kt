package team.ppac.detail

import androidx.compose.foundation.layout.size
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.SavedStateHandle
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import team.ppac.common.android.base.BaseViewModel
import team.ppac.common.kotlin.model.ReactionState
import team.ppac.designsystem.foundation.FarmemeIcon
import team.ppac.detail.mapper.toDetailMemeUiModel
import team.ppac.detail.mvi.DetailIntent
import team.ppac.detail.mvi.DetailSideEffect
import team.ppac.detail.mvi.DetailUiState
import team.ppac.domain.usecase.DeleteSavedMemeUseCase
import team.ppac.domain.usecase.EmitRefreshEventUseCase
import team.ppac.domain.usecase.GetMemeUseCase
import team.ppac.domain.usecase.ReactMemeUseCase
import team.ppac.domain.usecase.SaveMemeUseCase
import team.ppac.domain.usecase.ShareMemeUseCase
import team.ppac.errorhandling.FarmemeNetworkException
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val getMemeUseCase: GetMemeUseCase,
    private val saveMemeUseCase: SaveMemeUseCase,
    private val deleteSavedMemeUseCase: DeleteSavedMemeUseCase,
    private val reactMemeUseCase: ReactMemeUseCase,
    private val emitRefreshEventUseCase: EmitRefreshEventUseCase,
    private val shareMemeUseCase: ShareMemeUseCase,
) : BaseViewModel<DetailUiState, DetailSideEffect, DetailIntent>(savedStateHandle) {

    private val reactionState = ReactionState()

    init {
        launch {
            getMeme(currentState.memeId)
        }
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
                if (!reactionState.isUpdating && reactionState.isDoubleClickEvent()) {
                    incrementReactionCount()
                    postSideEffect(DetailSideEffect.RunRisingEffect)
                    reactionState.addReactionCount(1)
                    if (reactionState.isFirstClickEvent) {
                        reactionState.setIsFirstClickEvent(false)
                        launch {
                            updateReactionCountWithDelay()
                        }
                    }
                } else {
                    incrementReactionCount()
                    postSideEffect(DetailSideEffect.RunRisingEffect)
                    updateReactionCount(1)
                }
            }

            is DetailIntent.ClickBackButton -> {
                postSideEffect(DetailSideEffect.NavigateToBackEffect)
            }

            is DetailIntent.ClickBottomButton.Copy -> {
                postSideEffect(DetailSideEffect.CopyClipBoard)
            }

            is DetailIntent.ClickBottomButton.Share -> {
                incrementShareCount()
                postSideEffect(DetailSideEffect.ShareLink(intent.memeId))
            }

            is DetailIntent.ClickBottomButton.Farmeme -> {
                if (intent.isSavedMeme) {
                    deleteSavedMeme()
                    postSideEffect(DetailSideEffect.LogSaveMemeCancel)
                    showSnackbar(message = "파밈을 취소했어요")
                } else {
                    saveMeme()
                    postSideEffect(DetailSideEffect.LogSaveMeme)
                    showSnackbar(
                        message = "파밈 완료!",
                        icon = {
                            FarmemeIcon.BookmarkFilled(Modifier.size(20.dp))
                        }
                    )
                }
                emitRefreshEventUseCase()
            }

            is DetailIntent.ClickRetryButton -> {
                getMeme(currentState.memeId)
            }

            is DetailIntent.ClickHashtags -> {
                postSideEffect(DetailSideEffect.LogHashTagsClicked)
            }
        }
    }

    private suspend fun getMeme(memeId: String) {
        reduce { copy(isLoading = true) }
        val meme = getMemeUseCase(memeId)
        reduce {
            copy(
                detailMemeUiModel = meme.toDetailMemeUiModel(),
                isError = false,
                isLoading = false,
            )
        }
    }

    private suspend fun saveMeme() {
        reduce {
            copy(
                detailMemeUiModel = detailMemeUiModel
                    .copy(isSavedMeme = true)
            )
        }
        runCatching {
            saveMemeUseCase(currentState.memeId)
        }.onFailure {
            reduce {
                copy(
                    detailMemeUiModel = detailMemeUiModel
                        .copy(isSavedMeme = false)
                )
            }
        }
    }

    private suspend fun deleteSavedMeme() {
        reduce {
            copy(
                detailMemeUiModel = detailMemeUiModel
                    .copy(isSavedMeme = false)
            )
        }
        runCatching {
            deleteSavedMemeUseCase(currentState.memeId)
        }.onFailure {
            reduce {
                copy(
                    detailMemeUiModel = detailMemeUiModel
                        .copy(isSavedMeme = true)
                )
            }
        }
    }

    private fun incrementReactionCount() {
        reduce {
            copy(
                detailMemeUiModel = detailMemeUiModel.copy(
                    reactionCount = detailMemeUiModel.reactionCount + 1,
                    isReaction = true,
                )
            )
        }
    }

    private suspend fun updateReactionCountWithDelay() {
        delay(1000)
        reactionState.startUpdate()
        updateReactionCount(reactionState.reactionCount)
        reactionState.releaseState()
        reactionState.endUpdate()
    }

    private suspend fun updateReactionCount(reactionCount: Int) {
        runCatching {
            reactMemeUseCase(currentState.memeId, reactionCount)
        }.onFailure {
            Timber.tag(TAG).i("updateReactionCount failMessage= $it")
            reduce {
                copy(
                    detailMemeUiModel = detailMemeUiModel.copy(
                        reactionCount = detailMemeUiModel.reactionCount - reactionCount,
                        isReaction = false
                    )
                )
            }
        }
    }

    private suspend fun incrementShareCount() {
        shareMemeUseCase(currentState.memeId)
        emitRefreshEventUseCase()
    }

    companion object {
        const val TAG = "DetailViewModel"
    }
}