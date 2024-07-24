package team.ppac.designsystem.util.extension

import android.annotation.SuppressLint
import androidx.compose.foundation.LocalIndication
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.graphics.Color
import team.ppac.common.kotlin.model.MultipleEventsCutter

@SuppressLint("ModifierFactoryUnreferencedReceiver")
fun Modifier.noRippleClickable(
    enabled: Boolean = true,
    onClick: () -> Unit,
): Modifier = singleClickable(
    rippleColor = null,
    onClick = onClick,
).then(
    other = composed {
        clickable(
            indication = null,
            enabled = enabled,
            interactionSource = remember { MutableInteractionSource() },
            onClick = onClick,
        )
    }
)

@SuppressLint("ModifierFactoryUnreferencedReceiver")
fun Modifier.singleClickable(
    rippleColor: Color?,
    enabled: Boolean = true,
    debounceMillis: Long = 300L,
    onClick: () -> Unit,
): Modifier = composed {
    val multipleEventsCutter = remember { MultipleEventsCutter(debounceMillis) }
    clickable(
        interactionSource = remember { MutableInteractionSource() },
        enabled = enabled,
        indication = if (rippleColor != null) {
            rememberRipple(color = rippleColor)
        } else {
            LocalIndication.current
        },
        onClick = { multipleEventsCutter.processEvent(onClick) },
    )
}