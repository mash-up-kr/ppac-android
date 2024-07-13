package team.ppac.data.mapper

import team.ppac.domain.model.Keyword
import team.ppac.remote.model.response.keyword.TopKeywordResponse

internal fun TopKeywordResponse.toKeyword() = Keyword(
    id = keywordId,
    name = keywordName,
    searchCount = searchCount,
    createdAt = createdAt,
    updatedAt = updatedAt,
    category = category,
    imageUrl = imageUrl
)