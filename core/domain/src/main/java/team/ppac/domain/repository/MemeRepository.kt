package team.ppac.domain.repository

import team.ppac.domain.model.Meme

interface MemeRepository {
    suspend fun getMeme(memeId: String): Meme
    suspend fun getRecommendMemes(): List<Meme>
    suspend fun saveMeme(memeId: String): Boolean
    suspend fun deleteSavedMeme(memeId: String): Boolean
    suspend fun reactMeme(memeId: String): Boolean
}