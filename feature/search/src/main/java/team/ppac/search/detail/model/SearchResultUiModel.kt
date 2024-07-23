package team.ppac.search.detail.model

import team.ppac.domain.model.Meme

data class SearchResultUiModel(
    val memeId: String,
    val imageUrl: String,
    val memeTitle: String,
    val lolCount: Int,
)

fun Meme.toSearchResultUiModel() = SearchResultUiModel(
    memeId = id,
    imageUrl = imageUrl,
    memeTitle = title,
    lolCount = reactionCount
)
