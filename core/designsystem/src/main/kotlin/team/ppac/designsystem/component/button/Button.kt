package team.ppac.designsystem.component.button

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import team.ppac.designsystem.FarmemeTheme
import team.ppac.designsystem.foundation.FarmemeRadius

@Composable
fun FarmemeCircleButton(
    modifier: Modifier = Modifier,
    size: Dp = 50.dp,
    backgroundColor: Color,
    icon: @Composable () -> Unit,
    onClick: () -> Unit = { },
) {
    Box(
        modifier = modifier
            .size(size)
            .clip(FarmemeRadius.Radius40.shape)
            .background(color = backgroundColor)
            .clickable { onClick() },
        contentAlignment = Alignment.Center,
    ) {
        icon()
    }
}

@Composable
fun FarmemeWeakButton(
    modifier: Modifier = Modifier,
    backgroundColor: Color,
    icon: @Composable () -> Unit,
    text: String,
    textColor: Color,
    onClick: () -> Unit = { },
) {
    Row(
        modifier = modifier
            .wrapContentWidth()
            .height(50.dp)
            .clip(FarmemeRadius.Radius25.shape)
            .background(color = backgroundColor)
            .clickable { onClick() }
            .padding(15.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        icon()
        Spacer(Modifier.width(4.dp))
        Text(
            text = text,
            style = FarmemeTheme.typography.body.xLarge,
            color = textColor,
        )
    }
}

@Composable
fun FarmemeFilledButton(
    modifier: Modifier = Modifier,
    backgroundColor: Color,
    text: String,
    textColor: Color,
    onClick: () -> Unit = { },
) {
    Box(
        modifier = modifier
            .wrapContentWidth()
            .height(42.dp)
            .clip(FarmemeRadius.Radius10.shape)
            .background(color = backgroundColor)
            .clickable { onClick() }
            .padding(
                horizontal = 16.dp,
                vertical = 12.dp,
            ),
        contentAlignment = Alignment.Center,
    ) {
        Text(
            text = text,
            style = FarmemeTheme.typography.body.large,
            color = textColor,
        )
    }
}