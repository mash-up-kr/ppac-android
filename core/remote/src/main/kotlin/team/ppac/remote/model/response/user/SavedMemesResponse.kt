package team.ppac.remote.model.response.user

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import team.ppac.remote.model.response.meme.MemeResponse
import team.ppac.remote.model.response.pagination.PaginationResponse

@JsonClass(generateAdapter = true)
data class SavedMemesResponse(
    @field:Json(name = "pagination")
    val pagination: PaginationResponse,
    @field:Json(name = "memeList")
    val memeList: List<MemeResponse>,
)