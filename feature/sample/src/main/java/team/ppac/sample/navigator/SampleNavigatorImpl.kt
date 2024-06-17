package team.ppac.sample.navigator

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.activity.result.ActivityResultLauncher
import team.ppac.common.android.extension.getIntent
import team.ppac.navigator.SampleNavigator
import team.ppac.sample.SampleActivity

class SampleNavigatorImpl : SampleNavigator {

    override fun navigateFrom(
        activity: Activity,
        intentBuilder: Intent.() -> Intent,
        withFinish: Boolean,
    ) {
        activity.startActivity(activity.getIntent<SampleActivity>(intentBuilder))
        if (withFinish) activity.finish()
    }

    override fun navigateResultLauncher(
        launcher: ActivityResultLauncher<Intent>,
        activity: Activity,
        intentBuilder: Intent.() -> Intent,
    ) {
        launcher.launch(activity.getIntent<SampleActivity>(intentBuilder))
    }

    override fun navigateResultLauncher(
        launcher: ActivityResultLauncher<Intent>,
        context: Context,
        intentBuilder: Intent.() -> Intent,
    ) {
        launcher.launch(context.getIntent<SampleActivity>(intentBuilder))
    }
}