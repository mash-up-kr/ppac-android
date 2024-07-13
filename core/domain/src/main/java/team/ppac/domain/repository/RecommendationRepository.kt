package team.ppac.domain.repository

import team.ppac.domain.model.Meme

interface RecommendationRepository {
    suspend fun getThisWeekMemes(): List<Meme>
}