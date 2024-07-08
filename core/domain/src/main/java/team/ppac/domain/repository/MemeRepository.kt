package team.ppac.domain.repository

import kotlinx.collections.immutable.ImmutableList
import team.ppac.domain.model.Meme

interface MemeRepository {
    suspend fun getMeme(memeId: String): Meme
    suspend fun getRecommendMemes(): ImmutableList<Meme>
}