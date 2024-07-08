package team.ppac.domain.repository

import team.ppac.domain.model.User

interface UserRepository {
    suspend fun createUser(): Boolean
    suspend fun getUser(): User
}