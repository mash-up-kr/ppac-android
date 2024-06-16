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
    text: FarmemeText = FarmemeTheme.text,
    icon: FarmemeIcon = FarmemeTheme.icon,
    border: FarmemeBorder = FarmemeTheme.border,
    background: FarmemeBackground = FarmemeTheme.background,
    typography: FarmemeTypography = FarmemeTheme.typography,
    skeleton: FarmemeSkeleton = FarmemeTheme.skeleton,
    content: @Composable () -> Unit,
) {
    CompositionLocalProvider(
        LocalFarmemeText provides text,
        LocalFarmemeIcon provides icon,
        LocalFarmemeBorder provides border,
        LocalFarmemeBackground provides background,
        LocalFarmemeSkeleton provides skeleton,
        LocalFarmemeTypography provides typography,
        content = content
    )
}