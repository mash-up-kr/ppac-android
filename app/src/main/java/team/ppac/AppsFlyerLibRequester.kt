package team.ppac

import android.content.Context
import com.appsflyer.AppsFlyerLib
import com.appsflyer.deeplink.DeepLink
import com.appsflyer.deeplink.DeepLinkListener
import com.appsflyer.deeplink.DeepLinkResult
import timber.log.Timber

internal object AppsFlyerLibRequester {

    const val TAG = "AppsFlyerLibRequester"
    var memeId: String? = null

    fun initSdk(context: Context) {
        AppsFlyerLib.getInstance().init(BuildConfig.DEV_KEY, null, context)
        AppsFlyerLib.getInstance().start(context)
    }

    fun logOneLinkState() {
        AppsFlyerLib.getInstance().subscribeForDeepLink(object : DeepLinkListener {
            override fun onDeepLinking(deepLinkResult: DeepLinkResult) {
                when (deepLinkResult.status) {
                    DeepLinkResult.Status.FOUND -> {
                        Timber.tag(TAG).d("Deep link found")
                    }

                    DeepLinkResult.Status.NOT_FOUND -> {
                        Timber.tag(TAG).d("Deep link not found")
                        return
                    }

                    else -> {
                        // dlStatus == DeepLinkResult.Status.ERROR
                        val dlError = deepLinkResult.error
                        Timber.tag(TAG).d("There was an error getting Deep Link data: $dlError")
                        return
                    }
                }
                val deepLinkObj: DeepLink = deepLinkResult.deepLink
                try {
                    Timber.tag(TAG).d("The DeepLink data is: $deepLinkObj")
                } catch (e: Exception) {
                    Timber.tag(TAG).d("DeepLink data came back null")
                    return
                }

                // An example for using is_deferred
                if (deepLinkObj.isDeferred == true) {
                    Timber.tag(TAG).d("This is a deferred deep link")
                } else {
                    Timber.tag(TAG).d("This is a direct deep link")
                }

                try {
                    memeId = deepLinkObj.deepLinkValue
                    Timber.tag(TAG).d("The DeepLink will route to: " + memeId)

                } catch (e: Exception) {
                    Timber.tag(TAG).d("There's been an error: $e")
                    return
                }
            }
        })
    }
}