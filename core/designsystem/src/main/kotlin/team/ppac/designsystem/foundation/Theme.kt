package team.ppac.designsystem.foundation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable

object FarmemeTheme {
    val text: FarmemeText
        @Composable
        @ReadOnlyComposable
        get() = LocalFarmemeText.current

    val icon: FarmemeIcon
        @Composable
        @ReadOnlyComposable
        get() = LocalFarmemeIcon.current

    val border: FarmemeBorder
        @Composable
        @ReadOnlyComposable
        get() = LocalFarmemeBorder.current

    val background: FarmemeBackground
        @Composable
        @ReadOnlyComposable
        get() = LocalFarmemeBackground.current

    val skeleton: FarmemeSkeleton
        @Composable
        @ReadOnlyComposable
        get() = LocalFarmemeSkeleton.current

    val typography: FarmemeTypography
        @Composable
        @ReadOnlyComposable
        get() = LocalFarmemeTypography.current
}

@Composable
fun FarmemeTheme(
    typography: FarmemeTypography = FarmemeTheme.typography,
    content: @Composable () -> Unit,
) {
    CompositionLocalProvider(
        LocalFarmemeTypography provides typography,
        content = content
    )
}