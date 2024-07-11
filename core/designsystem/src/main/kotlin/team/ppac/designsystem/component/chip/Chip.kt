package team.ppac.designsystem.component.chip

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import team.ppac.designsystem.FarmemeTheme
import team.ppac.designsystem.foundation.FarmemeRadius
import team.ppac.designsystem.util.extension.noRippleClickable

@Composable
fun FarmemeSmallChip(
    modifier: Modifier = Modifier,
    chipColors: ChipColors = defaultChipColors(),
    text: String,
    enabled: Boolean = false,
) {
    val (textColor, backgroundColor) = if (enabled) {
        chipColors.textColor to chipColors.backgroundColor
    } else {
        chipColors.disabledTextColor to chipColors.disabledBackgroundColor
    }

    Box(
        modifier = modifier
            .width(65.dp)
            .clip(FarmemeRadius.Radius25.shape)
            .background(color = backgroundColor)
            .padding(vertical = 5.dp),
        contentAlignment = Alignment.Center,
    ) {
        Text(
            text = text,
            style = FarmemeTheme.typography.body.small.semibold,
            color = textColor,
        )
    }
}

@Composable
fun FarmemeMediumChip(
    modifier: Modifier = Modifier,
    text: String,
    onClick: () -> Unit = { },
) {
    Box(
        modifier = modifier
            .clip(FarmemeRadius.Radius20.shape)
            .background(color = FarmemeTheme.backgroundColor.assistive)
            .noRippleClickable(onClick = onClick)
            .padding(
                horizontal = 16.dp,
                vertical = 9.5.dp,
            ),
        contentAlignment = Alignment.Center,
    ) {
        Text(
            text = text,
            style = FarmemeTheme.typography.body.medium.medium,
            color = FarmemeTheme.textColor.primary,
        )
    }
}

data class ChipColors(
    val textColor: Color,
    val disabledTextColor: Color,
    val backgroundColor: Color,
    val disabledBackgroundColor: Color,
)

@Composable
fun defaultChipColors() = ChipColors(
    FarmemeTheme.textColor.brand,
    FarmemeTheme.textColor.secondary,
    FarmemeTheme.backgroundColor.brandAssistive,
    FarmemeTheme.backgroundColor.assistive,
)

@Composable
@Preview
fun FarmemeSmallChipEnabledPreview() {
    FarmemeSmallChip(
        text = "Text",
        enabled = true,
    )
}

@Composable
@Preview
fun FarmemeSmallChipDisabledPreview() {
    FarmemeSmallChip(
        text = "Text",
        enabled = false,
    )
}

@Composable
@Preview
fun FarmemeMediumChipPreview() {
    FarmemeMediumChip(
        text = "Text",
    )
}