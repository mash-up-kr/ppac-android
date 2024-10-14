package team.ppac.common.android.util

import android.content.Context
import android.content.Intent
import android.content.pm.PackageInfo
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.widget.Toast
import com.google.android.gms.common.ConnectionResult
import com.google.android.gms.common.GoogleApiAvailability
import com.google.firebase.ktx.Firebase
import com.google.firebase.remoteconfig.ConfigUpdate
import com.google.firebase.remoteconfig.ConfigUpdateListener
import com.google.firebase.remoteconfig.FirebaseRemoteConfigException
import com.google.firebase.remoteconfig.ktx.remoteConfig
import com.google.firebase.remoteconfig.ktx.remoteConfigSettings
import team.ppac.common.android.R
import timber.log.Timber

const val REMOTE_KEY_APP_VERSION = "latest_version"
const val TAG = "RemoteConfig"
fun remoteConfigInit() {
    val remoteConfig = Firebase.remoteConfig
    val configSettings = remoteConfigSettings {
        minimumFetchIntervalInSeconds = 3600
    }
    remoteConfig.setConfigSettingsAsync(configSettings)

    remoteConfig.fetchAndActivate().addOnCompleteListener { task ->
        val latestVersion = remoteConfig.getString(REMOTE_KEY_APP_VERSION)
        if (task.isSuccessful) {
            Timber.tag(TAG).i("remoteConfigInit - Success= $latestVersion")
        } else {
            Timber.tag(TAG).i("remoteConfigInit - Fail= $latestVersion")
        }
    }

    remoteConfig.addOnConfigUpdateListener(object : ConfigUpdateListener {
        override fun onUpdate(configUpdate: ConfigUpdate) {
            Timber.tag(TAG).d("Updated keys: ${configUpdate.updatedKeys}")
            if (configUpdate.updatedKeys.contains(REMOTE_KEY_APP_VERSION)) {
                remoteConfig.activate()
                    .addOnCompleteListener { task ->
                        val latestVersion = remoteConfig.getString(REMOTE_KEY_APP_VERSION)
                        if (task.isSuccessful) {
                            Timber.tag(TAG).i("remoteConfigInit update - Success=  $latestVersion")
                        } else {
                            Timber.tag(TAG).i("remoteConfigInit update - Fail= $latestVersion")
                        }
                    }
            }
        }

        override fun onError(error: FirebaseRemoteConfigException) {
            Timber.tag(TAG).w(error, "Config update error with code: ${error.code}")
        }
    })
}

fun needToUpdateVersion(context: Context): Boolean {
    val latestVersion = Firebase.remoteConfig.getString(REMOTE_KEY_APP_VERSION)
    val currentAppVersion =
        context.packageManager.getPackageInfoCompat(context.packageName).versionName
    Timber.tag(TAG).i("needToUpdateVersion: currentAppVersion= $currentAppVersion")
    return latestVersion.isNotEmpty() && latestVersion != currentAppVersion
}

fun launchGooglePlayStore(context: Context): Boolean {
    if (checkGooglePlayServices(context)) {
        val uri = "market://details?id=${context.packageName}"
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(uri))
        context.startActivity(intent)
        return true
    }
    return false
}

private fun checkGooglePlayServices(context: Context): Boolean {
    val googleApiAvailability = GoogleApiAvailability.getInstance()
    val status = googleApiAvailability.isGooglePlayServicesAvailable(context)
    if (status != ConnectionResult.SUCCESS) {
        Timber.tag(TAG).w("구글 플레이 스토어 열기 실패")
        Toast.makeText(
            context,
            context.getString(R.string.version_update_dialog_fail_toast),
            Toast.LENGTH_SHORT
        ).show()
        return false
    }
    return true
}

private fun PackageManager.getPackageInfoCompat(packageName: String, flags: Int = 0): PackageInfo =
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
        getPackageInfo(packageName, PackageManager.PackageInfoFlags.of(flags.toLong()))
    } else {
        @Suppress("DEPRECATION")
        getPackageInfo(packageName, flags)
    }
