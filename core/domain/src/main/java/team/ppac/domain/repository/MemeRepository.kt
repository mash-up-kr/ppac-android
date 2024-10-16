package team.ppac.domain.repository

import kotlinx.coroutines.flow.Flow
import team.ppac.domain.model.Meme
import team.ppac.domain.model.MemeWatchType
import team.ppac.domain.model.MemeWithPagination
import team.ppac.domain.model.ReactionMeme

interface MemeRepository {

    val savedMemeEventFlow: Flow<SavedMemeEvent>
    suspend fun getMeme(memeId: String): Meme
    suspend fun getRecommendMemes(): List<Meme>
    suspend fun saveMeme(memeId: String): Boolean
    suspend fun deleteSavedMeme(memeId: String): Boolean
    suspend fun getSearchMemes(
        keyword: String,
        getCurrentPage: (Int) -> Unit
    ): MemeWithPagination
    suspend fun reactMeme(memeId: String, count: Int): ReactionMeme
    suspend fun watchMeme(
        memeId: String,
        watchType: MemeWatchType,
    ): Boolean
    suspend fun searchMeme(query: String): MemeWithPagination
    suspend fun shareMeme(
        memeId: String
    ): Boolean
    suspend fun emitRefreshEvent()
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