package team.ppac.setting

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import team.ppac.setting.navigation.SETTING_ROUTE
import team.ppac.setting.navigation.navigateToPrivacyPolicy
import team.ppac.setting.navigation.privacyPolicyScreen
import team.ppac.setting.navigation.settingScreen

@Composable
fun SettingNavHost(
    modifier: Modifier = Modifier,
    startDestination: String = SETTING_ROUTE,
    navController: NavHostController,
    navigateToBack: () -> Unit,
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = startDestination,
    ) {
        settingScreen(
            navigateToPrivacyPolicy = { navController.navigateToPrivacyPolicy() },
            navigateToBack = navigateToBack,
        )
        privacyPolicyScreen(
            navigateToBack = { navController.popBackStack() }
        )
    }
}