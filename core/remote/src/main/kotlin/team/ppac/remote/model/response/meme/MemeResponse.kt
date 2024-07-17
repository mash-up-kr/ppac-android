package team.ppac.remote.model.response.meme

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import team.ppac.remote.model.response.keyword.KeywordResponse

@JsonClass(generateAdapter = true)
data class MemeResponse(
    @field:Json(name = "_id")
    val id: String,
    @field:Json(name = "image")
    val image: String,
    @field:Json(name = "isTodayMeme")
    val isTodayMeme: Boolean,
    @field:Json(name = "source")
    val source: String,
    @field:Json(name = "title")
    val title: String,
    @field:Json(name = "watch")
    val watch: Int,
    @field:Json(name = "reaction")
    val reaction: Int,
    @field:Json(name = "createdAt")
    val createdAt: String,
    @field:Json(name = "updatedAt")
    val updatedAt: String,
    @field:Json(name = "keywords")
    val keywords: List<KeywordResponse>?,
)
