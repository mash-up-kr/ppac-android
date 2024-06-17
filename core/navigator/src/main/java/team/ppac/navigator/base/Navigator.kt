package team.ppac.navigator.base

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.activity.result.ActivityResultLauncher

interface Navigator {
    fun navigateFrom(
        activity: Activity,
        intentBuilder: Intent.() -> Intent = { this },
        withFinish: Boolean = false,
    )

    fun navigateResultLauncher(
        launcher: ActivityResultLauncher<Intent>,
        activity: Activity,
        intentBuilder: Intent.() -> Intent = { this },
    )

    fun navigateResultLauncher(
        launcher: ActivityResultLauncher<Intent>,
        context: Context,
        intentBuilder: Intent.() -> Intent = { this },
    )
}