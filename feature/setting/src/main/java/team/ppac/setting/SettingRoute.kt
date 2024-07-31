package team.ppac.setting

import androidx.compose.runtime.Composable

@Composable
internal fun SettingRoute(
    navigateToBack: () -> Unit,
    navigateToPrivacyPolicy: () -> Unit,
) {
    SettingScreen(
        navigateToBack = navigateToBack,
        navigateToPrivacyPolicy = navigateToPrivacyPolicy,
    )
}