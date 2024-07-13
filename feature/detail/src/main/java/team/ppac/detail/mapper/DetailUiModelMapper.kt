package team.ppac.detail.mapper

import kotlinx.collections.immutable.toImmutableList
import team.ppac.detail.model.DetailMemeUiModel
import team.ppac.domain.model.Meme

internal fun Meme.toDetailMemeUiModel(): DetailMemeUiModel = DetailMemeUiModel(
    name = title,
    hashTags = keywords.toImmutableList(),
    sourceDescription = source,
)