package team.ppac

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.core.view.WindowCompat
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import team.ppac.analytics.AnalyticsHelper
import team.ppac.designsystem.FarmemeTheme
import team.ppac.designsystem.component.scaffold.FarmemeScaffold
import team.ppac.designsystem.component.scaffold.type.BackgroundColorType
import team.ppac.navigation.FarmemeNavHost
import team.ppac.navigation.component.FarmemeBottomBar
import team.ppac.navigation.navigateToTopLevelDestination
import team.ppac.navigator.DetailNavigator
import team.ppac.navigator.SettingNavigator
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var detailNavigator: DetailNavigator

    @Inject
    lateinit var settingNavigator: SettingNavigator

    @Inject
    lateinit var analyticsHelper: AnalyticsHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        val memeId = AppsFlyerLibRequester.memeId

        val navigateToDetail: (String) -> Unit = { memeId ->
            detailNavigator.navigateFrom(
                activity = this@MainActivity,
                intentBuilder = {
                    putExtra("memeId", memeId)
                },
            )
        }

        if (memeId != null) {
            navigateToDetail(memeId)
        }

        setContent {
            FarmemeTheme {
                val navController = rememberNavController()

                FarmemeScaffold(
                    modifier = Modifier.fillMaxSize(),
                    bottomBar = {
                        FarmemeBottomBar(
                            navigateToDestination = { navController.navigateToTopLevelDestination(it) },
                            currentDestination = navController.currentBackStackEntryAsState().value?.destination
                        )
                    },
                    isIncludeHorizontalPadding = false,
                    backgroundColorType = BackgroundColorType.SolidColor(FarmemeTheme.backgroundColor.white),
                ) {
                    FarmemeNavHost(
                        modifier = Modifier.fillMaxSize(),
                        analyticsHelper = analyticsHelper,
                        navController = navController,
                        navigateToDetail = { navigateToDetail(it) },
                        navigateToSetting = {
                            settingNavigator.navigateFrom(this@MainActivity)
                        },
                    )
                }
            }
        }
    }
}