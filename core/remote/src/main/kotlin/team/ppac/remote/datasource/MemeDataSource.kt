package team.ppac.remote.datasource

import team.ppac.remote.model.response.meme.MemeResponse

interface MemeDataSource {
    suspend fun getMemeById(memeId: String): MemeResponse
}