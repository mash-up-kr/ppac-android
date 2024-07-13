package team.ppac.search.main.model

import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.toImmutableList
import team.ppac.domain.model.RecommendKeyword

data class RecommendKeywordUiModel(
    val categoryHeader: String,
    val recommendKeywords: ImmutableList<String>,
)

fun RecommendKeyword.toRecommendKeywordUiModel() = RecommendKeywordUiModel(
    categoryHeader = category,
    recommendKeywords = keywords.toImmutableList()
)
