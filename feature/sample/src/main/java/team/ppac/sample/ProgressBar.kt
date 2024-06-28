package team.ppac.sample

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import team.ppac.designsystem.FarmemeTheme

@Composable
fun LinearProgressBar(
    modifier: Modifier = Modifier,
    progress: Float,
    strokeHeight: Dp = 8.dp,
    progressColor: Color = FarmemeTheme.backgroundColor.brand,
    backgroundColor: Color = Color.Gray.copy(alpha = 0.2f), // TODO 추후 변경 필요
) {
    Canvas(
        modifier = modifier
            .clip(RoundedCornerShape(4.dp))
            .height(strokeHeight)
            .fillMaxWidth(),
    ) {
        val strokeSize = size.height
        drawLinearIndicatorBackground(
            color = backgroundColor,
            strokeWidth = strokeSize,
        )
        drawLinearIndicator(
            startFraction = 0f,
            endFraction = progress,
            color = progressColor,
            strokeWidth = strokeSize,
        )
    }
}

private fun DrawScope.drawLinearIndicatorBackground(
    color: Color,
    strokeWidth: Float,
) = drawLinearIndicator(
    startFraction = 0f,
    endFraction = 1f,
    color = color,
    strokeWidth = strokeWidth,
)

private fun DrawScope.drawLinearIndicator(
    startFraction: Float,
    endFraction: Float,
    color: Color,
    strokeWidth: Float,
) {
    val width = size.width
    val height = size.height
    val yOffset = height / 2

    val isLtr = layoutDirection == LayoutDirection.Ltr
    val barStart = (if (isLtr) startFraction else 1f - endFraction) * width
    val barEnd = (if (isLtr) endFraction else 1f - startFraction) * width

    drawLine(
        color = color,
        start = Offset(barStart, yOffset),
        end = Offset(barEnd, yOffset),
        strokeWidth = strokeWidth,
        cap = StrokeCap.Round,
    )
}