package team.ppac.remote.model.response.meme

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import team.ppac.remote.model.response.pagination.PaginationResponse


@JsonClass(generateAdapter = true)
data class SearchMemeResponse(
    @field:Json(name = "pagination")
    val pagination: PaginationResponse,
    @field:Json(name = "memeList")
    val memeList: List<SearchResultResponse>,
)

@JsonClass(generateAdapter = true)
data class SearchResultResponse(
    @field:Json(name = "_id")
    val id: String,
    @field:Json(name = "image")
    val image: String,
    @field:Json(name = "isDeleted")
    val isDeleted: Boolean,
    @field:Json(name = "isTodayMeme")
    val isTodayMeme: Boolean,
    @field:Json(name = "keywordIds")
    val keywordIds: List<String>,
    @field:Json(name = "source")
    val source: String,
    @field:Json(name = "title")
    val title: String,
    @field:Json(name = "watch")
    val watchCount: Int,
    @field:Json(name = "reaction")
    val reactionCount: Int,
    @field:Json(name = "createdAt")
    val createdAt: String,
    @field:Json(name = "updatedAt")
    val updatedAt: String,
    @field:Json(name = "deviceId")
    val deviceId: String,
)
