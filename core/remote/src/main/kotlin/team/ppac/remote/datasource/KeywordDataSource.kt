package team.ppac.remote.datasource

import team.ppac.remote.model.response.keyword.RecommendKeywordResponse
import team.ppac.remote.model.response.keyword.TopKeywordResponse

interface KeywordDataSource {
    suspend fun getRecommendKeywords(): List<RecommendKeywordResponse>
    suspend fun getTopKeywords(): List<TopKeywordResponse>
}