package team.ppac.register.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import team.ppac.designsystem.FarmemeTheme
import team.ppac.designsystem.foundation.FarmemeRadius
import team.ppac.designsystem.util.extension.noRippleClickable

@Composable
internal fun RegisterButton(
    modifier: Modifier = Modifier,
    text: String,
    enabled: Boolean,
    onClick: () -> Unit = {},
) {
    val backgroundColor =
        if (enabled) FarmemeTheme.backgroundColor.primary else FarmemeTheme.backgroundColor.assistive
    val textColor =
        if (enabled) FarmemeTheme.textColor.inverse else FarmemeTheme.textColor.disabled

    Box(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp)
            .clip(FarmemeRadius.Radius10.shape)
            .background(color = backgroundColor)
            .noRippleClickable(
                onClick = onClick,
                enabled = enabled,
            )
            .padding(vertical = 14.dp),
        contentAlignment = Alignment.Center,
    ) {
        Text(
            text = text,
            style = FarmemeTheme.typography.heading.small.semibold,
            color = textColor,
        )
    }
}

@Preview
@Composable
private fun RegisterButtonPreview() {
    Column {
        RegisterButton(
            text = "등록하기",
            enabled = true,
        )
        RegisterButton(
            text = "등록하기",
            enabled = false,
        )
    }
}