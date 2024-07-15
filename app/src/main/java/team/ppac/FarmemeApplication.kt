package team.ppac

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

@HiltAndroidApp
class FarmemeApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        initTimber()
        AppsFlyerLibRequester.initSdk(this)
        AppsFlyerLibRequester.logOneLinkState()
    }

    private fun initTimber() {
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }
}