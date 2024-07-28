package team.ppac.common.android.util

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import team.ppac.designsystem.FarmemeTheme

fun Modifier.showSkeleton(
    isLoading: Boolean = false,
    viewType: SkeletonViewType = SkeletonViewType.Basic,
): Modifier {
    return if (!isLoading) {
        this
    } else {
        composed {
            val shadowWidth = with(LocalDensity.current) { 200.dp.toPx() }
            val duration = 1000
            val shimmerColors = when (viewType) {
                SkeletonViewType.Basic -> listOf(
                    FarmemeTheme.skeletonColor.primary,
                    FarmemeTheme.backgroundColor.white,
                    FarmemeTheme.skeletonColor.primary,
                )

                SkeletonViewType.Home -> listOf(
                    FarmemeTheme.backgroundColor.black.copy(alpha = 0.1f),
                    FarmemeTheme.backgroundColor.brandSubAssistive.copy(alpha = 0.7f),
                    FarmemeTheme.backgroundColor.black.copy(alpha = 0.1f),
                )
            }

            val transition = rememberInfiniteTransition(label = "")
            val translateAnimation = transition.animateFloat(
                initialValue = 0f,
                targetValue = duration + shadowWidth,
                animationSpec = infiniteRepeatable(
                    animation = tween(
                        durationMillis = duration,
                        easing = LinearEasing,
                    ),
                    repeatMode = RepeatMode.Restart,
                ),
                label = "",
            )

            background(
                brush = Brush.linearGradient(
                    colors = shimmerColors,
                    start = Offset(x = translateAnimation.value - shadowWidth, y = 0.0f),
                    end = Offset(x = translateAnimation.value, y = 0.0f),
                ),
            )
        }
    }
}

fun Modifier.visibility(isVisible: Boolean): Modifier {
    val alphaValue = if (isVisible) 1f else 0f
    return this then alpha(alpha = alphaValue)
}

enum class SkeletonViewType {
    Basic, Home
}