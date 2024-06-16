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
    textColor: FarmemeTextColor = FarmemeTheme.text,
    iconColor: FarmemeIconColor = FarmemeTheme.icon,
    borderColor: FarmemeBorderColor = FarmemeTheme.border,
    backgroundColor: FarmemeBackgroundColor = FarmemeTheme.background,
    skeletonColor: FarmemeSkeletonColor = FarmemeTheme.skeleton,
    typography: FarmemeTypography = FarmemeTheme.typography,
    content: @Composable () -> Unit,
) {
    CompositionLocalProvider(
        LocalFarmemeTextColor provides textColor,
        LocalFarmemeIconColor provides iconColor,
        LocalFarmemeBorderColor provides borderColor,
        LocalFarmemeBackgroundColor provides backgroundColor,
        LocalFarmemeSkeletonColor provides skeletonColor,
        LocalFarmemeTypography provides typography,
        content = content
    )
}