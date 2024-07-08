package team.ppac.data.mapper

import team.ppac.domain.model.User
import team.ppac.remote.model.response.user.UserResponse

internal fun UserResponse.toUser() = User(
    id = id,
    isDeleted = isDeleted,
    lastSeenMeme = lastSeenMeme,
    levelCount = level,
    reactionCount = reaction,
    saveCount = save,
    shareCount = share,
    watchCount = watch,
)