package team.ppac.remote.datasource

import team.ppac.remote.model.response.meme.MemeResponse

interface MemeDataSource {
    suspend fun getMemeById(memeId: String): MemeResponse
    suspend fun getRecommendMemes(): List<MemeResponse>
    suspend fun saveMeme(memeId: String): Boolean
    suspend fun deleteSavedMeme(memeId: String): Boolean
    suspend fun getSearchMemes(
        keyword: String,
        page: Int,
        size: Int
    ): List<MemeResponse>
    suspend fun reactMeme(memeId: String): Boolean
    suspend fun watchMeme(
        memeId: String,
        type: String,
    ): Boolean
}