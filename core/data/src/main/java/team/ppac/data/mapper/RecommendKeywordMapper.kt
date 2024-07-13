package team.ppac.data.mapper

import kotlinx.collections.immutable.toImmutableList
import team.ppac.domain.model.RecommendKeyword
import team.ppac.remote.model.response.keyword.RecommendKeywordResponse

internal fun RecommendKeywordResponse.toRecommendKeyword() = RecommendKeyword(
    category = category,
    keywords = recommendKeywords.map { it.toKeyword() }.toImmutableList()
)