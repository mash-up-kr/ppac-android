package team.ppac.search.main.model

import team.ppac.domain.model.Keyword

data class HotKeywordUiModel(
    val id: String,
    val keyword: String,
    val imageUrl: String,
)

fun Keyword.toHotKeywordUiModel() = HotKeywordUiModel(
    id = id,
    keyword = name,
    imageUrl = imageUrl ?: "",
)