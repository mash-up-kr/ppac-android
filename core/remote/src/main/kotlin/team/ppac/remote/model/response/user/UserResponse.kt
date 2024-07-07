package team.ppac.remote.model.response.user

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class UserResponse(
    @field:Json(name = "deviceId")
    val deviceId: String,
    @field:Json(name = "isDeleted")
    val isDeleted: Boolean,
    @field:Json(name = "lastSeenMeme")
    val lastSeenMeme: List<String>,
    @field:Json(name = "level")
    val level: Int,
    @field:Json(name = "memeRecommendWatchCount")
    val memeRecommendWatchCount: Int,
    @field:Json(name = "reaction")
    val reaction: Int,
    @field:Json(name = "save")
    val save: Int,
    @field:Json(name = "share")
    val share: Int,
    @field:Json(name = "watch")
    val watch: Int
)