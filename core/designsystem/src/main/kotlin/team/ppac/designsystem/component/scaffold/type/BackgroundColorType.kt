package team.ppac.designsystem.component.scaffold.type

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import team.ppac.designsystem.FarmemeTheme

sealed class BackgroundColorType {
    data class GradientColor(
        val brush: Brush
    ) : BackgroundColorType()

    data class SolidColor(
        val color: Color
    ) : BackgroundColorType()
}

@Composable
fun defaultColorType() = BackgroundColorType.SolidColor(FarmemeTheme.backgroundColor.white)