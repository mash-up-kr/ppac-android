package team.ppac.remote.api

import retrofit2.http.GET
import team.ppac.remote.model.response.keyword.RecommendKeywordResponse

internal interface KeywordApi {

    @GET("api/keyword/recommend")
    suspend fun getRecommendKeywords(): List<RecommendKeywordResponse>
}