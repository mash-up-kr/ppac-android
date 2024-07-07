package team.ppac.remote.api

import retrofit2.http.Body
import retrofit2.http.POST
import team.ppac.remote.model.request.user.PostUserRequest
import team.ppac.remote.model.response.Response
import team.ppac.remote.model.response.user.UserResponse

internal interface UserApi {
    @POST("api/user")
    suspend fun postUser(
        @Body postUserRequest: PostUserRequest,
    ): UserResponse
}