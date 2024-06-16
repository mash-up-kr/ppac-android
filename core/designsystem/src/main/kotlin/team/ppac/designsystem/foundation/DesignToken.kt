package team.ppac.designsystem.foundation

import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

data class FarmemeText(
    val primary: Color = Gray90,
    val secondary: Color = Gray70,
    val tertiary: Color = Gray60,
    val assistive: Color = Gray40,
    val disabled: Color = Gray30,
    val inverse: Color = White,
    val brand: Color = Orange100,
)

data class FarmemeIcon(
    val primary: Color = Gray90,
    val secondary: Color = Gray70,
    val tertiary: Color = Gray60,
    val assistive: Color = Gray40,
    val disabled: Color = Gray30,
    val inverse: Color = White,
    val brand: Color = Orange100,
)

data class FarmemeBorder(
    val primary: Color = Gray90,
    val secondary: Color = Gray20,
    val assistive: Color = Gray10,
)

data class FarmemeBackground(
    val primary: Color = Gray90,
    val assistive: Color = Gray10,
    val dimmer: Color = Alpha40,
    val brand: Color = Orange100,
)

data class FarmemeSkeleton(
    val primary: Color = Gray20,
    val secondary: Color = Gray30,
)

val LocalFarmemeText = staticCompositionLocalOf {
    FarmemeText()
}

val LocalFarmemeIcon = staticCompositionLocalOf {
    FarmemeIcon()
}

val LocalFarmemeBorder = staticCompositionLocalOf {
    FarmemeBorder()
}

val LocalFarmemeBackground = staticCompositionLocalOf {
    FarmemeBackground()
}

val LocalFarmemeSkeleton = staticCompositionLocalOf {
    FarmemeSkeleton()
}