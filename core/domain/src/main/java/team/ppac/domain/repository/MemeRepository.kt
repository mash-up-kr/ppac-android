package team.ppac.domain.repository

import team.ppac.domain.model.Meme

interface MemeRepository {
    suspend fun getMemeById(memeId: String): Meme
}