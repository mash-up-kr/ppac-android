package team.ppac.remote.interceptor

import kotlinx.serialization.json.Json
import okhttp3.Interceptor
import okhttp3.Response
import team.ppac.errorhandling.FarmemeException
import team.ppac.errorhandling.parseWithNetworkError
import javax.inject.Inject
import team.ppac.remote.model.response.Response as FarmemeResponse

class ErrorInterceptor @Inject constructor(
    private val json: Json,
) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        try {
            val response = chain.proceed(chain.request())
            if (response.isSuccessful) {
                return response
            }

            //에러가 발생 하는 경우
            response.body?.let {
                val errorBody = json.decodeFromString<FarmemeResponse<*>>(it.string())
                throw FarmemeException(
                    code = errorBody.code,
                    message = errorBody.message,
                )
            }
            return response
        } catch (e: Exception) {
            throw e.parseWithNetworkError()
        }
    }
}
