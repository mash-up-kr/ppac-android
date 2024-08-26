package team.ppac.search.main.model

import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.toImmutableList
import team.ppac.domain.model.RecommendKeyword

data class RecommendKeywordUiModel(
    val category: String,
    val recommendKeywords: ImmutableList<String>,
)

fun RecommendKeyword.toRecommendKeywordUiModel() = RecommendKeywordUiModel(
    category = category,
    recommendKeywords = keywords.map { it.name }.toImmutableList()
)
