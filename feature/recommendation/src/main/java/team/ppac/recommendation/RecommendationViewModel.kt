package team.ppac.recommendation

import androidx.lifecycle.SavedStateHandle
import dagger.hilt.android.lifecycle.HiltViewModel
import team.ppac.common.android.base.BaseViewModel
import team.ppac.recommendation.mvi.RecommendationIntent
import team.ppac.recommendation.mvi.RecommendationSideEffect
import team.ppac.recommendation.mvi.RecommendationState
import javax.inject.Inject

@HiltViewModel
class RecommendationViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
) : BaseViewModel<RecommendationState, RecommendationSideEffect, RecommendationIntent>(savedStateHandle) {

    override fun createInitialState(savedStateHandle: SavedStateHandle): RecommendationState {
        return RecommendationState.INITIAL_STATE
    }

    override suspend fun handleIntent(intent: RecommendationIntent) {
        when (intent) {
            else -> {
                println("")
            }
        }
    }
}