package team.ppac.recommendation

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.collections.immutable.toImmutableList
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import team.ppac.common.android.base.BaseViewModel
import team.ppac.domain.model.MemeWatchType
import team.ppac.domain.usecase.DeleteSavedMemeUseCase
import team.ppac.domain.usecase.GetThisWeekRecommendMemesUseCase
import team.ppac.domain.usecase.GetUserUseCase
import team.ppac.domain.usecase.ReactMemeUseCase
import team.ppac.domain.usecase.SaveMemeUseCase
import team.ppac.domain.usecase.WatchMemeUseCase
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
    init {
        initialAction()
    }

    override fun createInitialState(savedStateHandle: SavedStateHandle): RecommendationState {
        return RecommendationState.INITIAL_STATE
    }

    override suspend fun handleIntent(intent: RecommendationIntent) {
        when (intent) {
            is RecommendationIntent.ClickButton.LoL -> {
                postSideEffect(RecommendationSideEffect.RunRisingEffect)
                reduce {
                    updateReaction(intent.meme) {
                        it.copy(reactionCount = it.reactionCount + 1)
                    }
                }
                runCatching {
                    reactMemeUseCase(intent.meme.id)
                }.onFailure {
                    reduce {
                        updateReaction(intent.meme) { it.copy(reactionCount = it.reactionCount - 1) }
                    }
                }
            }

            is RecommendationIntent.ClickButton.Copy -> {
                postSideEffect(RecommendationSideEffect.CopyClipBoard(intent.memeIndex))
            }

            is RecommendationIntent.ClickButton.Share -> {
                postSideEffect(RecommendationSideEffect.ShareLink(intent.meme.id))
            }

            is RecommendationIntent.ClickButton.BookMark -> {
                reduce {
                    updateReaction(intent.meme) { it.copy(isSaved = it.isSaved.not()) }
                }
                runCatching {
                    if (intent.meme.isSaved) {
                        deleteSavedMemeUseCase(intent.meme.id)
                    } else {
                        saveMemeUseCase(intent.meme.id)
                    }
                }.onFailure {
                    reduce {
                        updateReaction(intent.meme) { it.copy(isSaved = it.isSaved.not()) }
                    }
                }
            }

            is RecommendationIntent.MovePage -> {
                if(intent.currentPage > currentState.currentPage){
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
        }
    }

    private fun initialAction() {
        viewModelScope.launch {
            val thisWeekMemesDeferred = async { getThisWeekRecommendMemesUseCase() }
            val userDeferred = async { getUserUseCase() }
            val user = userDeferred.await()
            val thisWeekMemes = thisWeekMemesDeferred.await()
            reduce {
                copy(
                    thisWeekMemes = thisWeekMemes.toImmutableList(),
                    seenMemeCount = user.memeRecommendWatchCount ?: 0,
                    currentPage = user.memeRecommendWatchCount ?: 0,
                )
            }
        }
    }
}