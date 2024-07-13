package team.ppac

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.rememberScaffoldState
import androidx.compose.ui.Modifier
import androidx.core.view.WindowCompat
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        setContent {
            FarmemeTheme {
                val navController = rememberNavController()
                val scaffoldState = rememberScaffoldState()

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
                    scaffoldState = scaffoldState,
                ) {
                    FarmemeNavHost(
                        modifier = Modifier.fillMaxSize(),
                        navController = navController,
                        onShowSnackBar = { _ -> false },
                        navigateToDetail = {
                            detailNavigator.navigateFrom(this@MainActivity)
                        },
                        navigateToSetting = {
                            settingNavigator.navigateFrom(this@MainActivity)
                        },
                    )
                }
            }
        }
    }
}