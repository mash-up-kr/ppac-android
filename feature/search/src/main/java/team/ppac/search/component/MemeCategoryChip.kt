package team.ppac.search.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import team.ppac.designsystem.FarmemeTheme
import team.ppac.designsystem.foundation.FarmemeRadius
import team.ppac.designsystem.util.extension.noRippleClickable

@Composable
internal fun MemeCategoryChip(
    modifier: Modifier = Modifier,
    title: String,
    onClick: () -> Unit,
) {
    Box(
        modifier = modifier
            .clip(FarmemeRadius.Radius20.shape)
            .background(
                color = FarmemeTheme.backgroundColor.assistive,
                shape = FarmemeRadius.Radius20.shape
            )
            .noRippleClickable(onClick = onClick)
            .padding(horizontal = 16.dp, vertical = 9.5.dp)
    ) {
        Text(
            text = title,
            style = FarmemeTheme.typography.body.medium.medium.copy(
                color = FarmemeTheme.textColor.primary
            )
        )
    }
}

@Preview
@Composable
private fun MemeCategoryChipPreview() {
    MemeCategoryChip(
        title = "현타",
        onClick = {}
    )
}