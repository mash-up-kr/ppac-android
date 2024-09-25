package team.ppac.recommendation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import team.ppac.designsystem.FarmemeTheme
import team.ppac.designsystem.foundation.FarmemeIcon
import team.ppac.designsystem.foundation.FarmemeRadius
import team.ppac.designsystem.util.extension.noRippleClickable

@Composable
internal fun UpLoadButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
) {
    BaseButton(
        modifier = modifier,
        backgroundColor = FarmemeTheme.backgroundColor.primary,
        text = "나도 밈 올리기",
        textColor = FarmemeTheme.textColor.inverse,
        icon = {
            FarmemeIcon.UpLoad()
        },
        onClick = onClick
    )
}

@Composable
private fun BaseButton(
    modifier: Modifier = Modifier,
    backgroundColor: Color,
    text: String,
    textColor: Color,
    onClick: () -> Unit = { },
    icon: @Composable () -> Unit,
) {
    Row(
        modifier = modifier
            .height(36.dp)
            .clip(FarmemeRadius.Radius10.shape)
            .background(color = backgroundColor)
            .noRippleClickable(onClick = onClick)
            .padding(horizontal = 15.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        icon()
        Spacer(Modifier.width(4.dp))
        Text(
            text = text,
            style = FarmemeTheme.typography.body.medium.semibold,
            color = textColor,
        )
    }
}
