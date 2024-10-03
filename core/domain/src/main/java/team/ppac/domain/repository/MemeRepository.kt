package team.ppac.domain.repository

import kotlinx.coroutines.flow.Flow
import team.ppac.domain.model.Meme
import team.ppac.domain.model.MemeWatchType
import team.ppac.domain.model.MemeWithPagination

interface MemeRepository {
    suspend fun getMeme(memeId: String): Meme
    suspend fun getRecommendMemes(): List<Meme>
    suspend fun saveMeme(memeId: String): Boolean
    suspend fun deleteSavedMeme(memeId: String): Boolean
    suspend fun getSearchMemes(
        keyword: String,
        getCurrentPage: (Int) -> Unit
    ): MemeWithPagination
    suspend fun reactMeme(memeId: String, count: Int): Boolean
    suspend fun watchMeme(
        memeId: String,
        watchType: MemeWatchType,
    ): Boolean
    suspend fun shareMeme(
        memeId: String
    ): Boolean
    suspend fun emitRefreshEvent()
    val savedMemeEventFlow: Flow<SavedMemeEvent>
    suspend fun uploadMeme(
        keywordIds: List<String>,
        memeImageUri: String,
        memeTitle: String,
        memeSource: String
    ): Boolean
}

sealed class SavedMemeEvent {
    data object Refresh : SavedMemeEvent()
}