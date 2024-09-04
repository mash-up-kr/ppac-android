package team.ppac.domain.repository

import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import team.ppac.domain.model.Meme
import team.ppac.domain.model.User

interface UserRepository {
    suspend fun createUser(): Boolean
    suspend fun getUser(): User
    fun getUserSavedMemes(getCurrentPage: (Int) -> Unit): Flow<PagingData<Meme>>
    suspend fun getUserRecentMemes(): List<Meme>
    suspend fun setLevel(level: Int)
    suspend fun getLevel() : Int
}