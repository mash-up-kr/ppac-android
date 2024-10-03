package team.ppac.remote.model.response.meme

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ReactionMemeResponse (
    @field:Json(name = "count")
    val count: Int,
)