package team.ppac.setting.policy

import androidx.compose.runtime.Composable

@Composable
internal fun PrivacyPolicyRoute(
    navigateToBack: () -> Unit,
) {
    PrivacyPolicyScreen(
        navigateToBack = navigateToBack,
    )
}