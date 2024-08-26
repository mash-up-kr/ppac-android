package team.ppac.setting

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import team.ppac.analytics.AnalyticsHelper
import team.ppac.setting.navigation.SETTING_ROUTE
import team.ppac.setting.navigation.navigateToPrivacyPolicy
import team.ppac.setting.navigation.privacyPolicyScreen
import team.ppac.setting.navigation.settingScreen

@Composable
fun SettingNavHost(
    modifier: Modifier = Modifier,
    analyticsHelper: AnalyticsHelper,
    startDestination: String = SETTING_ROUTE,
    navController: NavHostController,
    navigateToBack: () -> Unit,
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = startDestination,
        enterTransition = { EnterTransition.None },
        exitTransition = { ExitTransition.None }
    ) {
        settingScreen(
            analyticsHelper = analyticsHelper,
            navigateToPrivacyPolicy = { navController.navigateToPrivacyPolicy() },
            navigateToBack = navigateToBack,
        )
        privacyPolicyScreen(
            navigateToBack = { navController.popBackStack() }
        )
    }
}