package team.ppac.domain.repository

import team.ppac.domain.model.Meme

interface RecommendationRepository {
    suspend fun getLastSeenMemeCount(): Int
    suspend fun getThisWeekMemes(): List<Meme>
}