package team.ppac.remote.api

import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import team.ppac.remote.model.request.user.PostUserRequest
import team.ppac.remote.model.response.meme.MemeResponse
import team.ppac.remote.model.response.user.UserResponse

internal interface UserApi {
    @POST("api/user")
    suspend fun postUser(
        @Body postUserRequest: PostUserRequest,
    ): UserResponse

    @GET("api/user")
    suspend fun getUser(): UserResponse

    @GET("api/user/saved-memes")
    suspend fun getUserSavedMemes(): List<MemeResponse>

    @GET("api/user/recent-memes")
    suspend fun getUserRecentMemes(): List<MemeResponse>
}