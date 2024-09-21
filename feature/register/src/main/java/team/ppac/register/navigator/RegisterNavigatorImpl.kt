package team.ppac.register.navigator

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.activity.result.ActivityResultLauncher
import team.ppac.common.android.extension.getIntent
import team.ppac.navigator.RegisterNavigator
import team.ppac.register.RegisterActivity
import javax.inject.Inject

class RegisterNavigatorImpl @Inject constructor() : RegisterNavigator {
    override fun navigateFrom(
        activity: Activity,
        intentBuilder: Intent.() -> Intent,
        withFinish: Boolean
    ) {
        activity.startActivity(activity.getIntent<RegisterActivity>(intentBuilder))
        if (withFinish) activity.finish()
    }

    override fun navigateResultLauncher(
        launcher: ActivityResultLauncher<Intent>,
        activity: Activity,
        intentBuilder: Intent.() -> Intent
    ) {
        launcher.launch(activity.getIntent<RegisterActivity>(intentBuilder))
    }

    override fun navigateResultLauncher(
        launcher: ActivityResultLauncher<Intent>,
        context: Context,
        intentBuilder: Intent.() -> Intent
    ) {
        launcher.launch(context.getIntent<RegisterActivity>(intentBuilder))
    }
}