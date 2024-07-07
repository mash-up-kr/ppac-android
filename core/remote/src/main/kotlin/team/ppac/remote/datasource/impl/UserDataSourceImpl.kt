package team.ppac.remote.datasource.impl

import team.ppac.remote.api.UserApi
import team.ppac.remote.datasource.UserRemoteDataSource
import team.ppac.remote.model.request.user.PostUserRequest
import team.ppac.remote.model.response.user.UserResponse
import javax.inject.Inject

internal class UserDataSourceImpl @Inject constructor(
    private val userApi: UserApi
) : UserRemoteDataSource {
    override suspend fun postUser(deviceId: String): UserResponse {
        return userApi.postUser(postUserRequest = PostUserRequest(deviceId))
    }
}