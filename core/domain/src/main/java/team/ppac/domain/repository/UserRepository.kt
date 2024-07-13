package team.ppac.domain.repository

import team.ppac.domain.model.Meme
import team.ppac.domain.model.User

interface UserRepository {
    suspend fun createUser(): Boolean
    suspend fun getUser(): User
    suspend fun getUserSavedMemes(): List<Meme>
    suspend fun getUserRecentMemes(): List<Meme>
}