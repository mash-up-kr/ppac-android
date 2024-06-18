package team.ppac.designsystem

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable
import team.ppac.designsystem.foundation.FarmemeBackgroundColor
import team.ppac.designsystem.foundation.FarmemeBorderColor
import team.ppac.designsystem.foundation.FarmemeIconColor
import team.ppac.designsystem.foundation.FarmemeSkeletonColor
import team.ppac.designsystem.foundation.FarmemeTextColor
import team.ppac.designsystem.foundation.FarmemeTypography
import team.ppac.designsystem.foundation.LocalFarmemeBackgroundColor
import team.ppac.designsystem.foundation.LocalFarmemeBorderColor
import team.ppac.designsystem.foundation.LocalFarmemeIconColor
import team.ppac.designsystem.foundation.LocalFarmemeSkeletonColor
import team.ppac.designsystem.foundation.LocalFarmemeTextColor
import team.ppac.designsystem.foundation.LocalFarmemeTypography

object FarmemeTheme {
    val textColor: FarmemeTextColor
        @Composable
        @ReadOnlyComposable
        get() = LocalFarmemeTextColor.current

    val iconColor: FarmemeIconColor
        @Composable
        @ReadOnlyComposable
        get() = LocalFarmemeIconColor.current

    val borderColor: FarmemeBorderColor
        @Composable
        @ReadOnlyComposable
        get() = LocalFarmemeBorderColor.current

    val backgroundColor: FarmemeBackgroundColor
        @Composable
        @ReadOnlyComposable
        get() = LocalFarmemeBackgroundColor.current

    val skeletonColor: FarmemeSkeletonColor
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
    textColor: FarmemeTextColor = FarmemeTheme.textColor,
    iconColor: FarmemeIconColor = FarmemeTheme.iconColor,
    borderColor: FarmemeBorderColor = FarmemeTheme.borderColor,
    backgroundColor: FarmemeBackgroundColor = FarmemeTheme.backgroundColor,
    skeletonColor: FarmemeSkeletonColor = FarmemeTheme.skeletonColor,
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