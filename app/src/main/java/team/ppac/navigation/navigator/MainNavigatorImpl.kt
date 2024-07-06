package team.ppac.navigation.navigator

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.activity.result.ActivityResultLauncher
import team.ppac.MainActivity
import team.ppac.common.android.extension.getIntent
import team.ppac.navigator.MainNavigator
import javax.inject.Inject

internal class MainNavigatorImpl @Inject constructor() : MainNavigator {
    override fun navigateFrom(
        activity: Activity,
        intentBuilder: Intent.() -> Intent,
        withFinish: Boolean,
    ) {
        activity.startActivity(activity.getIntent<MainActivity>(intentBuilder))
        if (withFinish) activity.finish()
    }

    override fun navigateResultLauncher(
        launcher: ActivityResultLauncher<Intent>,
        activity: Activity,
        intentBuilder: Intent.() -> Intent,
    ) {
        launcher.launch(activity.getIntent<MainActivity>(intentBuilder))
    }

    override fun navigateResultLauncher(
        launcher: ActivityResultLauncher<Intent>,
        context: Context,
        intentBuilder: Intent.() -> Intent,
    ) {
        launcher.launch(context.getIntent<MainActivity>(intentBuilder))
    }
}