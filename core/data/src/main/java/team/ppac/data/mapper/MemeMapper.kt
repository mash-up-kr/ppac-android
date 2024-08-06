package team.ppac.data.mapper

import kotlinx.collections.immutable.toImmutableList
import team.ppac.domain.model.Meme
import team.ppac.remote.model.response.meme.MemeResponse

internal fun MemeResponse.toMeme(): Meme = Meme(
    id = id,
    imageUrl = image,
    isTodayMeme = isTodayMeme,
    source = source,
    title = title,
    watchCount = watch,
    reactionCount = reaction,
    createdAt = createdAt,
    updateAt = updatedAt,
    keywords = keywords?.map {
        it.toKeyword()
    }?.toImmutableList() ?: emptyList(),
    isSaved = isSaved,
    isReaction = isReaction,
)
