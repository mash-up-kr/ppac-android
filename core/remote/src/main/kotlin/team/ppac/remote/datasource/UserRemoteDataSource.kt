package team.ppac.remote.datasource

import team.ppac.remote.model.response.user.UserResponse

interface UserRemoteDataSource {
    suspend fun postUser(deviceId: String): UserResponse
    suspend fun getUser(): UserResponse
}