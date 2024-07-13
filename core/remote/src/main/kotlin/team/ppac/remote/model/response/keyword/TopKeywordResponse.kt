package team.ppac.remote.model.response.keyword

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class TopKeywordResponse(
    @field:Json(name = "_id")
    val keywordId: String,
    @field:Json(name = "name")
    val keywordName: String,
    @field:Json(name = "searchCount")
    val searchCount: Int,
    @field:Json(name = "createdAt")
    val createdAt: String,
    @field:Json(name = "updatedAt")
    val updatedAt: String,
    @field:Json(name = "category")
    val category: String,
    @field:Json(name = "topReactionImage")
    val imageUrl: String,
)
