package team.ppac.setting

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.core.view.WindowCompat
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import team.ppac.analytics.AnalyticsHelper
import team.ppac.common.android.util.noTransitionAnimation
import team.ppac.designsystem.FarmemeTheme
import javax.inject.Inject

@AndroidEntryPoint
class SettingActivity : ComponentActivity() {

    @Inject
    lateinit var analyticsHelper: AnalyticsHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        setContent {
            FarmemeTheme {
                val navController = rememberNavController()

                SettingNavHost(
                    modifier = Modifier.fillMaxSize(),
                    analyticsHelper = analyticsHelper,
                    navController = navController,
                    navigateToBack = { finish() },
                )
            }
        }
        noTransitionAnimation()
    }
}