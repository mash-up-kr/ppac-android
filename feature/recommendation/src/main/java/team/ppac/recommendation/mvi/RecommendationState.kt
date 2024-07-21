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

    fun changeReactionCount(
        meme: Meme,
        transform: (Int) -> Int
    ) = copy(
        thisWeekMemes = thisWeekMemes.map { changeMeme ->
            if (changeMeme.id == meme.id) {
                changeMeme.copy(
                    reaction = transform(changeMeme.reaction)
                )
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