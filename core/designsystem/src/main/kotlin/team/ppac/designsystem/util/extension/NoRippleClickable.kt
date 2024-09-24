package team.ppac.designsystem.util.extension

import android.annotation.SuppressLint
import androidx.compose.foundation.Indication
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.Role
import team.ppac.common.kotlin.model.MultipleEventsCutter

@SuppressLint("UnnecessaryComposedModifier")
fun Modifier.noRippleClickable(
    enabled: Boolean = true,
    onClickLabel: String? = null,
    role: Role? = null,
    onClick: () -> Unit,
): Modifier = composed {
    this then singleClickable(
        indication = null,
        enabled = enabled,
        onClickLabel = onClickLabel,
        role = role,
        onClick = onClick
    )
}

@SuppressLint("UnnecessaryComposedModifier")
fun Modifier.rippleClickable(
    rippleColor: Color = Color.Unspecified,
    enabled: Boolean = true,
    onClickLabel: String? = null,
    role: Role? = null,
    onClick: () -> Unit,
): Modifier = composed {
    this then singleClickable(
        indication = rememberRipple(color = rippleColor),
        enabled = enabled,
        onClickLabel = onClickLabel,
        role = role,
        onClick = onClick
    )
}

@SuppressLint("ModifierFactoryUnreferencedReceiver")
private fun Modifier.singleClickable(
    indication: Indication?,
    enabled: Boolean = true,
    onClickLabel: String? = null,
    role: Role? = null,
    debounceMillis: Long = 300L,
    onClick: () -> Unit,
): Modifier = composed {
    val multipleEventsCutter = remember { MultipleEventsCutter(debounceMillis) }

    clickable(
        interactionSource = remember { MutableInteractionSource() },
        indication = indication,
        enabled = enabled,
        onClickLabel = onClickLabel,
        role = role,
        onClick = { onClick() }, // Todo(hyejin.ju) 함수 분리해야함
    )
}
