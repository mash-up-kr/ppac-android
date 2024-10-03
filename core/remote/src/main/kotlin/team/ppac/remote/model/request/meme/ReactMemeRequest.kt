package team.ppac.remote.model.request.meme

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ReactMemeRequest (
    @field:Json(name = "count")
    val count: Int,
)