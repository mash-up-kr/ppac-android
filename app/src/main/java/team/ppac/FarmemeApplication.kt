package team.ppac

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import team.ppac.crashlytics.logger.CrashlyticsLoggerTree
import timber.log.Timber
import javax.inject.Inject

@HiltAndroidApp
class FarmemeApplication : Application() {

    @Inject
    lateinit var crashlyticsLoggerTree: CrashlyticsLoggerTree
    override fun onCreate() {
        super.onCreate()
        initTimber()
        AppsFlyerLibRequester.initSdk(this)
        AppsFlyerLibRequester.logOneLinkState()
    }

    private fun initTimber() {
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        } else {
            Timber.plant(crashlyticsLoggerTree)
        }
    }
}