package team.ppac.designsystem.foundation

import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

data class FarmemeTextColor(
    val primary: Color = Gray90,
    val secondary: Color = Gray70,
    val tertiary: Color = Gray60,
    val assistive: Color = Gray40,
    val disabled: Color = Gray30,
    val inverse: Color = White,
    val brand: Color = Orange100,
)

data class FarmemeIconColor(
    val primary: Color = Gray90,
    val secondary: Color = Gray70,
    val tertiary: Color = Gray60,
    val assistive: Color = Gray40,
    val disabled: Color = Gray30,
    val inverse: Color = White,
    val brand: Color = Orange100,
)

data class FarmemeBorderColor(
    val primary: Color = Gray90,
    val secondary: Color = Gray20,
    val assistive: Color = Gray10,
)

data class FarmemeBackgroundColor(
    val primary: Color = Gray90,
    val assistive: Color = Gray10,
    val dimmer: Color = BlackAlpha40,
    val brand: Color = Orange100,
    val brandassistive: Color = Orange10,
    val white: Color = White,
)

data class FarmemeSkeletonColor(
    val primary: Color = Gray20,
    val secondary: Color = Gray30,
)

val LocalFarmemeTextColor = staticCompositionLocalOf {
    FarmemeTextColor()
}

val LocalFarmemeIconColor = staticCompositionLocalOf {
    FarmemeIconColor()
}

val LocalFarmemeBorderColor = staticCompositionLocalOf {
    FarmemeBorderColor()
}

val LocalFarmemeBackgroundColor = staticCompositionLocalOf {
    FarmemeBackgroundColor()
}

val LocalFarmemeSkeletonColor = staticCompositionLocalOf {
    FarmemeSkeletonColor()
}