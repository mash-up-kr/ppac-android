package team.ppac.data.mapper

import team.ppac.domain.model.User
import team.ppac.remote.model.response.user.UserResponse

internal fun UserResponse.toUser() = User(
    id = id,
    isDeleted = isDeleted,
    lastSeenMeme = lastSeenMeme,
    level = level,
    reaction = reaction,
    save = save,
    share = share,
    watch = watch,
)