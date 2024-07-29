package team.ppac.remote.interceptor

import kotlinx.serialization.json.Json
import okhttp3.Interceptor
import okhttp3.Response
import okhttp3.ResponseBody.Companion.toResponseBody
import org.json.JSONObject
import team.ppac.errorhandling.FarmemeException
import team.ppac.errorhandling.parseWithNetworkError
import timber.log.Timber
import javax.inject.Inject
import team.ppac.remote.model.response.Response as FarmemeResponse

class ErrorInterceptor @Inject constructor(
    private val json: Json,
) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        try {
            val response = chain.proceed(chain.request())
            val responseBody = response.body
            if (responseBody != null && response.isSuccessful) {
                return response.newBuilder()
                    .body(JSONObject(responseBody.string())["data"].toString().toResponseBody())
                    .build()
            }
            responseBody?.let {
                val errorBody = json.decodeFromString<FarmemeResponse<*>>(it.string())
                throw FarmemeException(
                    code = errorBody.code,
                    message = errorBody.message,
                )
            }
            return response
        } catch (e: Exception) {
//            Timber.e(e)
            throw e.parseWithNetworkError()
        }
    }
}
