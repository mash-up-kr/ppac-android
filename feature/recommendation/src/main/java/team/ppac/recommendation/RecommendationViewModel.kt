package team.ppac.recommendation

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.collections.immutable.toImmutableList
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import team.ppac.common.android.base.BaseViewModel
import team.ppac.domain.usecase.GetThisWeekRecommendMemesUseCase
import team.ppac.domain.usecase.GetUserUseCase
import team.ppac.recommendation.mvi.RecommendationIntent
import team.ppac.recommendation.mvi.RecommendationSideEffect
import team.ppac.recommendation.mvi.RecommendationState
import javax.inject.Inject

@HiltViewModel
class RecommendationViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val getThisWeekRecommendMemesUseCase: GetThisWeekRecommendMemesUseCase,
    private val getUserUseCase: GetUserUseCase,
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
                    incrementReactionCount(intent.meme)
                }
            }

            else -> {
                println("")
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
                    seenMemeCount = user.memeRecommendWatchCount ?: 0
                )
            }
        }
    }
}