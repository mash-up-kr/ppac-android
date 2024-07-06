package team.ppac.remote.model.request.user

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PostUserRequest(
    @field:Json(name = "deviceID")
    val deviceId: String,
)
