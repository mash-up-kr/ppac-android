package team.ppac.setting.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import team.ppac.setting.policy.PrivacyPolicyRoute

const val PRIVACY_POLICY_ROUTE = "privacy_policy"

fun NavController.navigateToPrivacyPolicy() = navigate(PRIVACY_POLICY_ROUTE)

fun NavGraphBuilder.privacyPolicyScreen(
    navigateToBack: () -> Unit,
) {
    composable(
        route = PRIVACY_POLICY_ROUTE,
    ) {
        PrivacyPolicyRoute(
            navigateToBack = navigateToBack
        )
    }
}