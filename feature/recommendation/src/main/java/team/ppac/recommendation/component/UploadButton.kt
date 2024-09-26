package team.ppac.recommendation.component

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.drawscope.rotate
import androidx.compose.ui.unit.dp
import team.ppac.designsystem.FarmemeTheme
import team.ppac.designsystem.foundation.FarmemeIcon
import team.ppac.designsystem.foundation.FarmemeRadius
import team.ppac.designsystem.util.extension.noRippleClickable

@Composable
internal fun UploadButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
) {
    val transition = rememberInfiniteTransition(label = "transition")
    val angle by transition.animateFloat(
        initialValue = 0f,
        targetValue = 360f,
        animationSpec = infiniteRepeatable(
            animation = tween(1000, easing = LinearEasing),
            repeatMode = RepeatMode.Restart,
        ),
        label = "angle animation"
    )
    val brush = Brush.sweepGradient(
        colors = buildList {
            add(FarmemeTheme.backgroundColor.brand)
            add(FarmemeTheme.backgroundColor.primary)
        }
    )
    Box(
        modifier = modifier
            .clip(FarmemeRadius.Radius10.shape)
            .noRippleClickable(onClick = onClick),
        contentAlignment = Alignment.Center
    ) {
        Box(
            modifier = Modifier
                .clip(FarmemeRadius.Radius10.shape)
                .padding(1.5.dp)
                .drawWithContent {
                    rotate(angle) {
                        drawCircle(
                            brush = brush,
                            radius = size.width,
                        )
                    }
                    drawContent()
                },
        ) {
            Row(
                modifier = Modifier
                    .height(36.dp)
                    .clip(FarmemeRadius.Radius10.shape)
                    .background(color = FarmemeTheme.backgroundColor.primary)
                    .padding(horizontal = 15.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                FarmemeIcon.Upload()
                Spacer(Modifier.width(4.dp))
                Text(
                    text = "나도 밈 올리기",
                    style = FarmemeTheme.typography.body.medium.semibold,
                    color = FarmemeTheme.textColor.inverse,
                )
            }
        }
    }

}