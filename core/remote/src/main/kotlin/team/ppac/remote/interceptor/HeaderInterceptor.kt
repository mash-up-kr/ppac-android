package team.ppac.remote.interceptor

import androidx.datastore.core.DataStore
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.Response
import team.ppac.core.datastore.UserData
import javax.inject.Inject

class HeaderInterceptor @Inject constructor(
    private val userDataStore: DataStore<UserData>,
) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val builder = chain.request().newBuilder()
        val userData = runBlocking {
            userDataStore.data.firstOrNull()
        }
        if (userData != UserData.getDefaultInstance() && userData != null) {
            builder.addHeader(HEADER_DEVICE_ID_KEY, userData.userId)
        }
        return chain.proceed(builder.build())
    }

    companion object {
        const val HEADER_DEVICE_ID_KEY = "x-device-id"
    }
}