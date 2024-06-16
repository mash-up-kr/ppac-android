package team.ppac.designsystem.foundation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable

object FarmemeTheme {
    val text: FarmemeTextColor
        @Composable
        @ReadOnlyComposable
        get() = LocalFarmemeTextColor.current

    val icon: FarmemeIconColor
        @Composable
        @ReadOnlyComposable
        get() = LocalFarmemeIconColor.current

    val border: FarmemeBorderColor
        @Composable
        @ReadOnlyComposable
        get() = LocalFarmemeBorderColor.current

    val background: FarmemeBackgroundColor
        @Composable
        @ReadOnlyComposable
        get() = LocalFarmemeBackgroundColor.current

    val skeleton: FarmemeSkeletonColor
        @Composable
        @ReadOnlyComposable
        get() = LocalFarmemeSkeletonColor.current

    val typography: FarmemeTypography
        @Composable
        @ReadOnlyComposable
        get() = LocalFarmemeTypography.current
}

@Composable
fun FarmemeTheme(
    text: FarmemeTextColor = FarmemeTheme.text,
    icon: FarmemeIconColor = FarmemeTheme.icon,
    border: FarmemeBorderColor = FarmemeTheme.border,
    background: FarmemeBackgroundColor = FarmemeTheme.background,
    skeleton: FarmemeSkeletonColor = FarmemeTheme.skeleton,
    typography: FarmemeTypography = FarmemeTheme.typography,
    content: @Composable () -> Unit,
) {
    CompositionLocalProvider(
        LocalFarmemeTextColor provides text,
        LocalFarmemeIconColor provides icon,
        LocalFarmemeBorderColor provides border,
        LocalFarmemeBackgroundColor provides background,
        LocalFarmemeSkeletonColor provides skeleton,
        LocalFarmemeTypography provides typography,
        content = content
    )
}