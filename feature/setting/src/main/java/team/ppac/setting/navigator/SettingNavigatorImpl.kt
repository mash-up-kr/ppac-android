package team.ppac.setting.navigator

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.activity.result.ActivityResultLauncher
import team.ppac.common.android.extension.getIntent
import team.ppac.navigator.SettingNavigator
import team.ppac.setting.SettingActivity
import javax.inject.Inject

class SettingNavigatorImpl @Inject constructor() : SettingNavigator {
    override fun navigateFrom(
        activity: Activity,
        intentBuilder: Intent.() -> Intent,
        withFinish: Boolean
    ) {
        activity.startActivity(activity.getIntent<SettingActivity>(intentBuilder))
        if (withFinish) activity.finish()
    }

    override fun navigateResultLauncher(
        launcher: ActivityResultLauncher<Intent>,
        activity: Activity,
        intentBuilder: Intent.() -> Intent
    ) {
        launcher.launch(activity.getIntent<SettingActivity>(intentBuilder))
    }

    override fun navigateResultLauncher(
        launcher: ActivityResultLauncher<Intent>,
        context: Context,
        intentBuilder: Intent.() -> Intent
    ) {
        launcher.launch(context.getIntent<SettingActivity>(intentBuilder))
    }
}