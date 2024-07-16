package team.ppac.remote.model.response.pagination

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PaginationResponse(
    @field:Json(name = "total")
    val total: Int,
    @field:Json(name = "page")
    val page: Int,
    @field:Json(name = "perPage")
    val perPage: Int,
    @field:Json(name = "currentPage")
    val currentPage: Int,
    @field:Json(name = "totalPages")
    val totalPages: Int,
)