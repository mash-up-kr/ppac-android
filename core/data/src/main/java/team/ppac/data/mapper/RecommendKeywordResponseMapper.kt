package team.ppac.data.mapper

import team.ppac.domain.model.RecommendKeyword
import team.ppac.remote.model.response.keyword.RecommendKeywordResponse

internal fun RecommendKeywordResponse.toRecommendKeyword() = RecommendKeyword(
    recommendKeywordCategory = category,
    recommendKeywords = recommendKeywords
)