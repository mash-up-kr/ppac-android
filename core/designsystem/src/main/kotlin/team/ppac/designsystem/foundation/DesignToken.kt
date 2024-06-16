package team.ppac.designsystem.foundation

import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

data class FarmemeBorder(
    val primary: Color = Gray90,
    val secondary: Color = Gray20,
    val assistive: Color = Gray10,
)

val LocalFarmemeBorder = staticCompositionLocalOf {
    FarmemeBorder()
}