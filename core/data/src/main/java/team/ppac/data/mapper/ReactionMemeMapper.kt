package team.ppac.data.mapper

import team.ppac.domain.model.ReactionMeme
import team.ppac.remote.model.response.meme.ReactionMemeResponse

internal fun ReactionMemeResponse.toReactionMeme(): ReactionMeme = ReactionMeme(
    count = count,
)