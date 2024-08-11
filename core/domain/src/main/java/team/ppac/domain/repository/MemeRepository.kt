package team.ppac.domain.repository

import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import team.ppac.domain.model.Meme
import team.ppac.domain.model.MemeWatchType
import team.ppac.domain.model.MemeWithPagination

interface MemeRepository {
    suspend fun getMeme(memeId: String): Meme
    suspend fun getRecommendMemes(): List<Meme>
    suspend fun saveMeme(memeId: String): Boolean
    suspend fun deleteSavedMeme(memeId: String): Boolean
    suspend fun getSearchMemes(keyword: String): MemeWithPagination
    suspend fun reactMeme(memeId: String): Boolean
    suspend fun watchMeme(
        memeId: String,
        watchType: MemeWatchType,
    ): Boolean
}