package team.ppac.designsystem.util.extension

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import team.ppac.common.kotlin.model.MultipleEventsCutter

@SuppressLint("ModifierFactoryUnreferencedReceiver")
fun Modifier.noRippleClickable(
    enabled: Boolean = true,
    onClick: () -> Unit,
    debounceMillis: Long = 300L,
): Modifier = composed {
    val multipleEventsCutter = remember { MultipleEventsCutter(debounceMillis) }
    clickable(
        indication = null,
        enabled = enabled,
        interactionSource = remember { MutableInteractionSource() },
        onClick = { multipleEventsCutter.processEvent(onClick) },
    )
}