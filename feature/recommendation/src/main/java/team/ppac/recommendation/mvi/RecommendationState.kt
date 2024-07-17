package team.ppac.recommendation.mvi

import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf
import kotlinx.collections.immutable.toImmutableList
import team.ppac.common.android.base.UiState
import team.ppac.domain.model.Meme

data class RecommendationState(
    val isLoading: Boolean = true,
    val seenMemeCount: Int = 0,
    val thisWeekMemes: ImmutableList<Meme> = persistentListOf(),
) : UiState {

    fun incrementReactionCount(meme: Meme) =  copy(
        thisWeekMemes = thisWeekMemes.map {
            if (it == meme) {
                it.copy(reaction = it.reaction + 1)
            } else {
                it
            }
        }.toImmutableList(),
    )
    companion object {
        val INITIAL_STATE
            get() = RecommendationState()
    }
}