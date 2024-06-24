package team.ppac.remote.model.response

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import team.ppac.errorhandling.FarmemeException

@JsonClass(generateAdapter = true)
data class Response<T : Any>(
    @field:Json(name = "status")
    val status: String,
    @field:Json(name = "code")
    val code: Int,
    @field:Json(name = "message")
    val message: String,
    @field:Json(name = "data")
    val data: T?,
)

fun <T : Any> Response<T>.catchException(): T {
    return if (status != "success" || data == null) {
        throw FarmemeException(
            code = code,
            message = message
        )
    } else data
}