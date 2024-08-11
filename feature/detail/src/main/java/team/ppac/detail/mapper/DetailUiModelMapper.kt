package team.ppac.detail.mapper

import kotlinx.collections.immutable.toImmutableList
import team.ppac.detail.model.DetailMemeUiModel
import team.ppac.domain.model.Meme

internal fun Meme.toDetailMemeUiModel(): DetailMemeUiModel = DetailMemeUiModel(
    imageUrl = imageUrl,
    name = title,
    hashTags = keywords.map { it.name }.toImmutableList(),
    sourceDescription = "출처 : ".plus(source),
    isSavedMeme = isSaved,
    reactionCount = reactionCount,
    isReaction = isReaction,
)