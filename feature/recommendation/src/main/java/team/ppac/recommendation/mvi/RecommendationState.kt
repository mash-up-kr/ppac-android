package team.ppac.recommendation.mvi

import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf
import kotlinx.collections.immutable.toImmutableList
import team.ppac.common.android.base.UiState
import team.ppac.domain.model.Meme

data class RecommendationState(
    val isLoading: Boolean = true,
    val isError: Boolean = false,
    val isRefreshing: Boolean = false,
    val currentPage: Int = 0,
    val seenMemeCount: Int = 0,
    val level: Int = 0,
    val thisWeekMemes: ImmutableList<Meme> = persistentListOf(),
) : UiState {

    fun updateReaction(
        meme: Meme,
        transform: (Meme) -> Meme
    ) = copy(
        thisWeekMemes = thisWeekMemes.map { changeMeme ->
            if (changeMeme.id == meme.id) {
                transform(changeMeme)
            } else {
                changeMeme
            }
        }.toImmutableList(),
    )

    companion object {
        val INITIAL_STATE
            get() = RecommendationState()
    }
}