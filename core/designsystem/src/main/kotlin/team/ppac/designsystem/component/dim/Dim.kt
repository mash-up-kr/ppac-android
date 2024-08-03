package team.ppac.designsystem.component.dim

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import team.ppac.designsystem.FarmemeTheme

@Composable
fun BoxScope.FarmemeImageDim(
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier
            .matchParentSize()
            .background(FarmemeTheme.backgroundColor.black.copy(alpha = 0.04f))
    )
}