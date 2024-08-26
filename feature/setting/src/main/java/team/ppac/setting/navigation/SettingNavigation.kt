package team.ppac.setting.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import team.ppac.analytics.AnalyticsHelper
import team.ppac.setting.SettingRoute

const val SETTING_ROUTE = "setting"

fun NavGraphBuilder.settingScreen(
    analyticsHelper: AnalyticsHelper,
    navigateToPrivacyPolicy: () -> Unit,
    navigateToBack: () -> Unit,
) {
    composable(
        route = SETTING_ROUTE
    ) {
        SettingRoute(
            analyticsHelper = analyticsHelper,
            navigateToBack = navigateToBack,
            navigateToPrivacyPolicy = navigateToPrivacyPolicy,
        )
    }
}