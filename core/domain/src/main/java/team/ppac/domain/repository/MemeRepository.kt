package team.ppac.domain.repository

import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import team.ppac.domain.model.Meme
import team.ppac.domain.model.MemeWatchType

interface MemeRepository {
    suspend fun getMeme(memeId: String): Meme
    suspend fun getRecommendMemes(): List<Meme>
    suspend fun saveMeme(memeId: String): Boolean
    suspend fun deleteSavedMeme(memeId: String): Boolean
    fun getSearchMemes(keyword: String): Flow<PagingData<Meme>>
    suspend fun reactMeme(memeId: String): Boolean
    suspend fun watchMeme(
        memeId: String,
        watchType: MemeWatchType,
    ): Boolean

    suspend fun emitRefreshEvent()
    val savedMemeEventFlow: Flow<SavedMemeEvent>
}

sealed class SavedMemeEvent {
    data object Refresh : SavedMemeEvent()
}