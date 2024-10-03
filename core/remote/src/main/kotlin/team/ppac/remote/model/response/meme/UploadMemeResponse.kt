package team.ppac.remote.model.response.meme

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class UploadMemeResponse(
    @field:Json(name = "deviceId")
    val deviceId: String,
    @field:Json(name = "title")
    val title: String,
    @field:Json(name = "keywordIds")
    val keywords: List<String>?,
    @field:Json(name = "image")
    val image: String,
    @field:Json(name = "reaction")
    val reaction: Int,
    @field:Json(name = "source")
    val source: String,
    @field:Json(name = "isTodayMeme")
    val isTodayMeme: Boolean,
    @field:Json(name = "isDeleted")
    val isDeleted: Boolean,
    @field:Json(name = "_id")
    val id: String,
    @field:Json(name = "createdAt")
    val createdAt: String,
    @field:Json(name = "updatedAt")
    val updatedAt: String,
)
