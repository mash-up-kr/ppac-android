package team.ppac

import android.app.Application
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.crashlytics.FirebaseCrashlytics
import dagger.hilt.android.HiltAndroidApp
import team.ppac.crashlytics.logger.CrashlyticsLoggerTree
import team.ppac.local.datasource.AppConfig
import timber.log.Timber
import javax.inject.Inject

@HiltAndroidApp
class FarmemeApplication : Application() {

    @Inject
    lateinit var crashlyticsLoggerTree: CrashlyticsLoggerTree

    @Inject
    lateinit var analytics: FirebaseAnalytics

    @Inject
    lateinit var crashlytics: FirebaseCrashlytics

    @Inject
    lateinit var appConfig: AppConfig

    private val deviceId by lazy { appConfig.deviceId }

    override fun onCreate() {
        super.onCreate()
        initTimber()
        initAnalytics()
        initCrashlytics()
        initAppsFlyer()
    }

    private fun initTimber() {
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        } else {
            Timber.plant(crashlyticsLoggerTree)
        }
    }

    private fun initAnalytics() {
        analytics.setUserId(deviceId)
    }

    private fun initCrashlytics() {
        crashlytics.setUserId(deviceId)
    }

    private fun initAppsFlyer() {
        AppsFlyerLibRequester.initSdk(this)
        AppsFlyerLibRequester.logOneLinkState()
    }
}