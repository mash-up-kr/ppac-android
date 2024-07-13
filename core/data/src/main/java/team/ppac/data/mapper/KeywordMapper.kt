package team.ppac.data.mapper

import team.ppac.domain.model.Keyword
import team.ppac.remote.model.response.keyword.KeywordResponse

fun KeywordResponse.toKeyword() = Keyword(
    id = id,
    name = name,
)