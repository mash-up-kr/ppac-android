package team.ppac.crashlytics.logger

import android.util.Log
import com.google.firebase.crashlytics.FirebaseCrashlytics
import timber.log.Timber
import javax.inject.Inject

class CrashlyticsLoggerTree @Inject constructor(
    private val crashlytics: FirebaseCrashlytics
) : Timber.Tree() {

    override fun log(priority: Int, tag: String?, message: String, t: Throwable?) {
        if (priority != Log.ERROR) return

        when {
            t != null -> crashlytics.recordException(t)
//            else -> crashlytics.log(message)
        }
    }
}