package team.ppac.data.mapper

import team.ppac.domain.model.Meme
import team.ppac.remote.model.response.meme.MemeResponse

internal fun MemeResponse.toMeme(): Meme = Meme(
    id = id,
    image = image,
    isTodayMeme = isTodayMeme,
    source = source,
    title = title,
    watch = watch,
    reaction = reaction,
    createdAt = createdAt,
    updateAt = updatedAt,
    keywords = keywords,
)
