package team.ppac.search.main.model

import team.ppac.domain.model.Keyword

data class HotKeywordUiModel(
    val keywordId: String,
    val keyword: String,
    val imageUrl: String,
)

fun Keyword.toHotKeywordUiModel() = HotKeywordUiModel(
    keywordId = id,
    keyword = name,
    imageUrl = imageUrl ?: "",
)