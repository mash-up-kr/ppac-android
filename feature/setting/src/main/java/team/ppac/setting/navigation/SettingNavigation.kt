package team.ppac.setting.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import team.ppac.setting.SettingRoute

const val SETTING_ROUTE = "setting"

fun NavGraphBuilder.settingScreen(
    navigateToPrivacyPolicy: () -> Unit,
    navigateToBack: () -> Unit,
) {
    composable(
        route = SETTING_ROUTE
    ) {
        SettingRoute(
            navigateToBack = navigateToBack,
            navigateToPrivacyPolicy = navigateToPrivacyPolicy,
        )
    }
}