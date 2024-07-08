package team.ppac.detail.mapper

import team.ppac.detail.model.DetailMemeUiModel
import team.ppac.domain.model.Meme

internal fun Meme.toDetailMemeUiModel(): DetailMemeUiModel = DetailMemeUiModel(
    name = title,
    hashTags = keywords,
    sourceDescription = source,
)