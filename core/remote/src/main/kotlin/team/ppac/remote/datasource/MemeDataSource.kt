package team.ppac.remote.datasource

import team.ppac.remote.model.response.meme.MemeResponse
import team.ppac.remote.model.response.meme.ReactionMemeResponse
import team.ppac.remote.model.response.user.MemesResponse

interface MemeDataSource {
    suspend fun getMemeById(memeId: String): MemeResponse

    suspend fun getRecommendMemes(): List<MemeResponse>

    suspend fun saveMeme(memeId: String): Boolean

    suspend fun deleteSavedMeme(memeId: String): Boolean

    suspend fun getSearchMemes(
        keyword: String,
        page: Int,
        size: Int,
    ): MemesResponse
    suspend fun reactMeme(memeId: String, count: Int): ReactionMemeResponse

    suspend fun watchMeme(
        memeId: String,
        type: String,
    ): Boolean

    suspend fun shareMeme(memeId: String): Boolean

    suspend fun uploadMeme(
        keywordIds: List<String>,
        memeImageUri: String,
        memeTitle: String,
        memeSource: String
    ): Boolean

    suspend fun searchMeme(
        query: String,
        page: Int,
        size: Int,
    ): SavedMemesResponse
}