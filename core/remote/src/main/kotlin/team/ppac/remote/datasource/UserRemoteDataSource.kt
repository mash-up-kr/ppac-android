package team.ppac.remote.datasource

import team.ppac.remote.model.response.meme.MemeResponse
import team.ppac.remote.model.response.user.MemesResponse
import team.ppac.remote.model.response.user.UserResponse

interface UserRemoteDataSource {
    suspend fun postUser(deviceId: String): UserResponse
    suspend fun getUser(): UserResponse
    suspend fun getUserSavedMemes(page: Int, size: Int): MemesResponse
    suspend fun getUserRegisteredMemes(page: Int, size: Int): MemesResponse
    suspend fun getUserRecentMemes(): List<MemeResponse>
}