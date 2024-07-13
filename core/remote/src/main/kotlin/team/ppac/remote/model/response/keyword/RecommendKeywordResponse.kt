package team.ppac.remote.model.response.keyword

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class RecommendKeywordResponse(
    @field:Json(name = "keywords")
    val recommendKeywords: List<KeywordResponse>,
    @field:Json(name = "category")
    val category: String,
)
