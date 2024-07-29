package team.ppac.setting

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import team.ppac.designsystem.util.extension.noRippleClickable

@Composable
internal fun PrivacyPolicyScreen(
    modifier: Modifier = Modifier,
    navigateToBack: () -> Unit,
) {
    Text(
        modifier = modifier
            .fillMaxSize()
            .noRippleClickable {
                navigateToBack()
            },
        text = "PrivacyPolicy"
    )
}