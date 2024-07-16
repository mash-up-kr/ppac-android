package team.ppac.remote.api

import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query
import team.ppac.remote.model.response.meme.MemeResponse

internal interface MemeApi {
    @GET("api/meme/{memeId}")
    suspend fun getMemeById(@Path("memeId") memeId: String): MemeResponse

    @GET("api/meme/recommend-memes")
    suspend fun getRecommendMemes(
        @Query("size")
        size: Int = 5,
    ): List<MemeResponse>

    @POST("/api/meme/{memeId}/save")
    suspend fun saveMeme(@Path("memeId") memeId:String): Boolean

    @DELETE("/api/meme/{memeId}/save")
    suspend fun deleteSavedMeme(@Path("memeId") memeId:String): Boolean

}
