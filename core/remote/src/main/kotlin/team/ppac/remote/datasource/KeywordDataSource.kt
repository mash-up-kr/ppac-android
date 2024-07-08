package team.ppac.remote.datasource

import team.ppac.remote.model.response.keyword.RecommendKeywordResponse

interface KeywordDataSource {

    suspend fun getRecommendKeywords(): List<RecommendKeywordResponse>
}