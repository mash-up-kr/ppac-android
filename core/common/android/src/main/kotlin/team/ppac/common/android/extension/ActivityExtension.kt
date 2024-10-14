package team.ppac.common.android.extension

import android.app.Activity
import android.content.Intent
import android.net.Uri
import androidx.core.app.ActivityCompat
import kotlin.system.exitProcess

inline fun <reified T : Activity> Activity.getIntent(
    intentBuilder: Intent.() -> Intent = { this },
): Intent {
    return intentBuilder(Intent(this, T::class.java).apply {
        addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
    })
}

fun Activity.forceKillApplication() {
    ActivityCompat.finishAffinity(this)
    exitProcess(0)
}

fun Activity.openExternalWebView(url: String) {
    val webUrl = if (url.startsWith("http")) url else "https://$url"

    val intent = Intent()
    intent.action = Intent.ACTION_VIEW
    intent.data = Uri.parse(webUrl)
    startActivity(intent)
}