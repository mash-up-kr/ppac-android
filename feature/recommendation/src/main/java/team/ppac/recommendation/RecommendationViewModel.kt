package team.ppac.recommendation

import androidx.compose.foundation.layout.size
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.SavedStateHandle
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.collections.immutable.toImmutableList
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import team.ppac.common.android.base.BaseViewModel
import team.ppac.common.kotlin.model.ReactionState
import team.ppac.designsystem.foundation.FarmemeIcon
import team.ppac.domain.model.Meme
import team.ppac.domain.model.MemeWatchType
import team.ppac.domain.usecase.DeleteSavedMemeUseCase
import team.ppac.domain.usecase.GetThisWeekRecommendMemesUseCase
import team.ppac.domain.usecase.GetUserUseCase
import team.ppac.domain.usecase.ReactMemeUseCase
import team.ppac.domain.usecase.SaveMemeUseCase
import team.ppac.domain.usecase.WatchMemeUseCase
import team.ppac.errorhandling.FarmemeNetworkException
import team.ppac.recommendation.mvi.RecommendationIntent
import team.ppac.recommendation.mvi.RecommendationSideEffect
import team.ppac.recommendation.mvi.RecommendationState
import javax.inject.Inject

@HiltViewModel
class RecommendationViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val getThisWeekRecommendMemesUseCase: GetThisWeekRecommendMemesUseCase,
    private val getUserUseCase: GetUserUseCase,
    private val reactMemeUseCase: ReactMemeUseCase,
    private val saveMemeUseCase: SaveMemeUseCase,
    private val deleteSavedMemeUseCase: DeleteSavedMemeUseCase,
    private val watchMemeUseCase: WatchMemeUseCase,
) : BaseViewModel<RecommendationState, RecommendationSideEffect, RecommendationIntent>(
    savedStateHandle
) {
    private val reactionState = ReactionState()

    init {
        launch {
            initialAction()
        }
    }

    override fun createInitialState(savedStateHandle: SavedStateHandle): RecommendationState {
        return RecommendationState.INITIAL_STATE
    }

    override fun handleClientException(throwable: Throwable) {
        if (throwable is FarmemeNetworkException) {
            reduce {
                copy(isError = true)
            }
        }
    }

    override suspend fun handleIntent(intent: RecommendationIntent) {
        when (intent) {
            is RecommendationIntent.ClickButton.LoL -> {
                if (!reactionState.isUpdating && reactionState.isDoubleClickEvent()) {
                    postSideEffect(RecommendationSideEffect.RunRisingEffect(intent.meme))
                    reduce {
                        updateReaction(intent.meme) {
                            it.copy(
                                reactionCount = it.reactionCount + 1,
                                isReaction = true,
                            )
                        }
                    }
                    reactionState.addReactionCount(1)
                    if (reactionState.isFirstClickEvent) {
                        reactionState.setIsFirstClickEvent(false)
                        launch {
                            updateReactionCountWithDelay(intent.meme)
                        }
                    }
                } else {
                    postSideEffect(RecommendationSideEffect.RunRisingEffect(intent.meme))
                    reduce {
                        updateReaction(intent.meme) {
                            it.copy(
                                reactionCount = it.reactionCount + 1,
                                isReaction = true,
                            )
                        }
                    }
                    updateReactionCount(intent.meme, 1)
                }
            }

            is RecommendationIntent.ClickButton.Copy -> {
                postSideEffect(RecommendationSideEffect.CopyClipBoard(intent.selectedMemeIndex))
            }

            is RecommendationIntent.ClickButton.Share -> {
                postSideEffect(RecommendationSideEffect.ShareLink(intent.meme.id))
            }

            is RecommendationIntent.ClickButton.BookMark -> {
                reduce {
                    updateReaction(intent.meme) { it.copy(isSaved = it.isSaved.not()) }
                }
                if (intent.meme.isSaved) {
                    deleteSavedMemeUseCase(intent.meme.id)
                    postSideEffect(RecommendationSideEffect.LogSaveMemeCancel(intent.meme))
                    showSnackbar(message = "파밈을 취소했어요")
                } else {
                    saveMemeUseCase(intent.meme.id)
                    postSideEffect(RecommendationSideEffect.LogSaveMeme(intent.meme))
                    showSnackbar(
                        message = "파밈 완료!",
                        icon = {
                            FarmemeIcon.BookmarkFilled(Modifier.size(20.dp))
                        }
                    )
                }
            }

            is RecommendationIntent.ClickButton.HashTags -> {
                postSideEffect(RecommendationSideEffect.LogHashTagsClicked)
            }

            is RecommendationIntent.MovePage -> {
                if (intent.currentPage > currentState.currentPage) {
                    reduce {
                        copy(
                            currentPage = intent.currentPage,
                            seenMemeCount = currentState.seenMemeCount + 1
                        )
                    }
                }
                runCatching {
                    watchMemeUseCase(intent.meme.id, MemeWatchType.RECOMMEND)
                } // 에러 무시
            }

            RecommendationIntent.PullRefresh -> {
                reduce { copy(isRefreshing = true) }
                initialAction()
                delay(500L)
                reduce { copy(isRefreshing = false) }
            }

            RecommendationIntent.Init -> {
                initialAction()
                reduce {
                    copy(isError = false)
                }
            }
        }
    }

    private suspend fun initialAction() {
        coroutineScope {
            val thisWeekMemesDeferred = async { getThisWeekRecommendMemesUseCase() }
            val userDeferred = async { getUserUseCase() }
            val user = userDeferred.await()
            val thisWeekMemes = thisWeekMemesDeferred.await()
            reduce {
                copy(
                    isLoading = false,
                    thisWeekMemes = thisWeekMemes.toImmutableList(),
                    seenMemeCount = user.memeRecommendWatchCount ?: 1,
                    currentPage = user.memeRecommendWatchCount?.minus(1) ?: 0,
                    level = user.levelCount,
                )
            }
        }
    }

    private suspend fun updateReactionCountWithDelay(meme: Meme) {
        delay(1000)
        reactionState.startUpdate()
        updateReactionCount(meme, reactionState.reactionCount) // Todo(hyejin.ju) API 연결 해야함
        reactionState.releaseState()
        reactionState.endUpdate()
    }

    private suspend fun updateReactionCount(meme: Meme, reactionCount: Int) {
        runCatching {
            reactMemeUseCase(meme.id)
        }.onFailure {
            reduce {
                updateReaction(meme) {
                    it.copy(
                        reactionCount = it.reactionCount - 1,
                        isReaction = false
                    )
                }
            }
        }
    }

}