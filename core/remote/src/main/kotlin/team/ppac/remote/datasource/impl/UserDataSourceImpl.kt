package team.ppac.remote.datasource.impl

import team.ppac.remote.api.UserApi
import team.ppac.remote.datasource.UserRemoteDataSource
import team.ppac.remote.model.request.user.PostUserRequest
import team.ppac.remote.model.response.meme.MemeResponse
import team.ppac.remote.model.response.user.UserResponse
import javax.inject.Inject

internal class UserDataSourceImpl @Inject constructor(
    private val userApi: UserApi
) : UserRemoteDataSource {
    override suspend fun postUser(deviceId: String): UserResponse {
        return userApi.postUser(postUserRequest = PostUserRequest(deviceId))
    }

    override suspend fun getUser(): UserResponse {
        return userApi.getUser()
    }

    override suspend fun getUserSavedMemes(): List<MemeResponse> {
        return userApi.getUserSavedMemes()
    }

    override suspend fun getUserRecentMemes(): List<MemeResponse> {
        return userApi.getUserRecentMemes()
    }
}