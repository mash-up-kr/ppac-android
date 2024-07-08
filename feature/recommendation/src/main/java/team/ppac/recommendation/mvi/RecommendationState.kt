package team.ppac.recommendation.mvi

import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf
import team.ppac.common.android.base.UiState
import team.ppac.domain.model.Meme

data class RecommendationState(
    val isLoading: Boolean = true,
    val seenMemeCount: Int = 0,
    val thisWeekMemes: ImmutableList<Meme> = persistentListOf(),
) : UiState {
    companion object {
        val INITIAL_STATE
            get() = RecommendationState()
    }
}