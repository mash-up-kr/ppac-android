package team.ppac.remote.model.response.keyword

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class KeywordResponse(
    @field:Json(name = "_id")
    val id: String,
    @field:Json(name = "name")
    val name: String,
)