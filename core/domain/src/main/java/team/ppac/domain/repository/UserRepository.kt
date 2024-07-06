package team.ppac.domain.repository

interface UserRepository {
    suspend fun createUser(): Boolean
}