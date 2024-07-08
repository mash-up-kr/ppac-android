package team.ppac.remote.api

import retrofit2.http.GET
import retrofit2.http.Path
import team.ppac.remote.model.response.meme.MemeResponse

internal interface MemeApi {
    @GET("api/meme/{memeId}")
    suspend fun getMemeById(@Path("memeId") memeId: String) : MemeResponse

}