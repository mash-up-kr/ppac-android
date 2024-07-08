package team.ppac.domain.repository

import kotlinx.collections.immutable.ImmutableList
import team.ppac.domain.model.Meme

interface RecommendationRepository {
    suspend fun getThisWeekMemes(): ImmutableList<Meme>
}