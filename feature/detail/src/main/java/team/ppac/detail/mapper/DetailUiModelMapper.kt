package team.ppac.detail.mapper

import kotlinx.collections.immutable.toImmutableList
import team.ppac.detail.model.DetailMemeUiModel
import team.ppac.domain.model.Meme

internal fun Meme.toDetailMemeUiModel(): DetailMemeUiModel = DetailMemeUiModel(
    imageUrl = imageUrl,
    name = title,
    hashTags = keywords.map { it.name }.toImmutableList(),
    sourceDescription = source,
    isSavedMeme = isSaved,
    reactionCount = reaction,
)