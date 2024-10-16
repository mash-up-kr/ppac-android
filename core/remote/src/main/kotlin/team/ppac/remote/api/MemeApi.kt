package team.ppac.remote.api

import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part
import retrofit2.http.Path
import retrofit2.http.Query
import team.ppac.remote.model.request.meme.ReactMemeRequest
import team.ppac.remote.model.response.meme.MemeResponse
import team.ppac.remote.model.response.meme.ReactionMemeResponse
import team.ppac.remote.model.response.meme.SearchMemeResponse
import team.ppac.remote.model.response.meme.UploadMemeResponse
import team.ppac.remote.model.response.user.MemesResponse

internal interface MemeApi {
    @GET("api/meme/{memeId}")
    suspend fun getMemeById(@Path("memeId") memeId: String): MemeResponse

    @GET("api/meme/recommend-memes")
    suspend fun getRecommendMemes(
        @Query("size")
        size: Int = 5,
    ): List<MemeResponse>

    @POST("/api/meme/{memeId}/save")
    suspend fun saveMeme(@Path("memeId") memeId: String): Boolean

    @DELETE("/api/meme/{memeId}/save")
    suspend fun deleteSavedMeme(@Path("memeId") memeId: String): Boolean

    @GET("/api/meme/search/{name}")
    suspend fun getSearchMemes(
        @Path("name") keyword: String,
        @Query("page") page: Int,
        @Query("size") pageSize: Int,
    ): MemesResponse

    @POST("/api/meme/{memeId}/reaction")
    suspend fun reactMeme(
        @Path("memeId") memeId: String,
        @Body reactMemeRequest: ReactMemeRequest
    ): ReactionMemeResponse

    @POST("/api/meme/{memeId}/watch/{type}")
    suspend fun watchMeme(
        @Path("memeId") memeId: String,
        @Path("type") type: String,
    ): Boolean

    @POST("/api/meme/{memeId}/share")
    suspend fun shareMeme(
        @Path("memeId") memeId: String,
    ): Boolean

    @Multipart
    @POST("/api/meme")
    suspend fun postMeme(
        @Part image: MultipartBody.Part,
        @Part("title") title: RequestBody,
        @Part("source") source: RequestBody,
        @Part("keywordIds[]") keywordIds: ArrayList<RequestBody>,
    ): UploadMemeResponse

    // Search
    @GET("/api/meme/search")
    suspend fun searchMeme(
        @Query("q") query: String,
        @Query("page") page: Int,
        @Query("size") pageSize: Int,
    ): SearchMemeResponse
}
