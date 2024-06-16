package team.ppac.common.android.extension

import android.app.Activity
import android.content.Intent

inline fun <reified T : Activity> Activity.getIntent(
    intentBuilder: Intent.() -> Intent = { this },
): Intent {
    return intentBuilder(Intent(this, T::class.java))
}