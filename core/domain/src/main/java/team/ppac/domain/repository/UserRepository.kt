package team.ppac.domain.repository

import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import team.ppac.domain.model.Meme
import team.ppac.domain.model.User

interface UserRepository {
    suspend fun createUser(): Boolean
    suspend fun getUser(): User
    fun getUserSavedMemes(): Flow<PagingData<Meme>>
    suspend fun getUserRecentMemes(): List<Meme>
}