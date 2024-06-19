package team.ppac.designsystem.component.button

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
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