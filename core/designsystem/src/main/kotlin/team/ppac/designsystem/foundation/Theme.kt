package team.ppac.designsystem.foundation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable

object FarmemeTheme {
    val border: FarmemeBorder
        @Composable
        @ReadOnlyComposable
        get() = LocalFarmemeBorder.current

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