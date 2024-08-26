package team.ppac.detail

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.core.view.WindowCompat
import dagger.hilt.android.AndroidEntryPoint
import team.ppac.analytics.AnalyticsHelper
import team.ppac.common.android.util.noTransitionAnimation
import team.ppac.designsystem.FarmemeTheme
import javax.inject.Inject

@AndroidEntryPoint
class DetailActivity : ComponentActivity() {

    @Inject
    lateinit var analyticsHelper: AnalyticsHelper
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        setContent {
            FarmemeTheme {
                DetailRoute(
                    analyticsHelper = analyticsHelper,
                    navigateToBack = { finish() }
                )
            }
        }
        noTransitionAnimation()
    }
}