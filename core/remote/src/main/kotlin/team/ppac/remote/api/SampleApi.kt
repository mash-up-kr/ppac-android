package team.ppac.remote.api

import retrofit2.http.GET
import team.ppac.remote.model.response.Response
import team.ppac.remote.model.response.sample.SampleResponse

internal interface SampleApi {
    @GET("v2/list")
    suspend fun getImages(): List<SampleResponse>


    /** 가이드 용 **/
    @GET("")
    suspend fun getSample(): Response<List<SampleResponse>>
}