package team.ppac.designsystem.component.button

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import team.ppac.designsystem.FarmemeTheme
import team.ppac.designsystem.foundation.FarmemeIcon
import team.ppac.designsystem.foundation.FarmemeRadius
import team.ppac.designsystem.util.extension.noRippleClickable

@Composable
fun FarmemeCircleButton(
    modifier: Modifier = Modifier,
    size: Dp = 50.dp,
    backgroundColor: Color,
    onClick: () -> Unit = { },
    icon: @Composable () -> Unit,
) {
    Box(
        modifier = modifier
            .size(size)
            .clip(FarmemeRadius.Radius40.shape)
            .background(color = backgroundColor)
            .noRippleClickable(onClick = onClick),
        contentAlignment = Alignment.Center,
    ) {
        icon()
    }
}

@Composable
fun FarmemeWeakButton(
    modifier: Modifier = Modifier,
    backgroundColor: Color = FarmemeTheme.backgroundColor.white,
    text: String = "",
    textColor: Color = FarmemeTheme.textColor.primary,
    withStar: Boolean = false,
    isDebounceClick: Boolean = true,
    onClick: () -> Unit = { },
    icon: @Composable () -> Unit,
) {
    Row(
        modifier = modifier
            .clip(FarmemeRadius.Radius25.shape)
            .background(color = backgroundColor)
            .noRippleClickable(onClick = onClick, isDebounceClick = isDebounceClick)
            .padding(15.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        icon()
        Spacer(Modifier.width(4.dp))
        Text(
            text = text,
            style = FarmemeTheme.typography.body.xLarge.semibold,
            color = textColor,
        )
        if (withStar) {
            Spacer(Modifier.width(4.dp))
            FarmemeIcon.Required(modifier = Modifier.align(Alignment.Top))
        }
    }
}

@Composable
fun FarmemeFilledButton(
    modifier: Modifier = Modifier,
    backgroundColor: Color = FarmemeTheme.backgroundColor.primary,
    text: String,
    textColor: Color = FarmemeTheme.textColor.inverse,
    onClick: () -> Unit = { },
) {
    Box(
        modifier = modifier
            .clip(FarmemeRadius.Radius10.shape)
            .background(color = backgroundColor)
            .noRippleClickable(onClick = onClick)
            .padding(
                horizontal = 16.dp,
                vertical = 12.dp,
            ),
        contentAlignment = Alignment.Center,
    ) {
        Text(
            text = text,
            style = FarmemeTheme.typography.body.large.semibold,
            color = textColor,
        )
    }
}

@Composable
@Preview
private fun FarmemeCircleButtonPreview() {
    FarmemeCircleButton(
        backgroundColor = FarmemeTheme.backgroundColor.assistive,
        icon = { FarmemeIcon.Copy(Modifier.size(20.dp)) },
    )
}

@Composable
@Preview
private fun FarmemeWeakButtonPreview() {
    Column {
        FarmemeWeakButton(
            backgroundColor = FarmemeTheme.backgroundColor.assistive,
            icon = { FarmemeIcon.Copy(Modifier.size(20.dp)) },
            text = "버튼",
            textColor = FarmemeTheme.textColor.primary,
        )
        FarmemeWeakButton(
            backgroundColor = FarmemeTheme.backgroundColor.assistive,
            icon = { FarmemeIcon.Copy(Modifier.size(20.dp)) },
            text = "버튼",
            textColor = FarmemeTheme.textColor.primary,
            withStar = true,
        )
    }
}

@Composable
@Preview
private fun FarmemeFilledButtonPreview() {
    FarmemeFilledButton(
        text = "버튼",
    )
}