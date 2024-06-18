package team.ppac.designsystem.foundation

import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import team.ppac.designsystem.R

object FarmemeIcon {
    @Composable
    fun Back24(
        modifier: Modifier = Modifier,
        tint: Color = Color.Unspecified,
    ) = Icon(
        modifier = modifier,
        painter = painterResource(R.drawable.ic_back_24),
        contentDescription = null,
        tint = tint
    )
}