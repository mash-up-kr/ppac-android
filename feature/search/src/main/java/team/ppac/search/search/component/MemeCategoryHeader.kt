package team.ppac.search.search.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import team.ppac.designsystem.FarmemeTheme
import team.ppac.search.search.preview.MemeCategoryHeaderProvider

@Composable
internal fun MemeCategoryHeader(
    modifier: Modifier = Modifier,
    title: String,
) {
    Text(
        modifier = modifier
            .fillMaxWidth()
            .padding(
                start = 20.dp,
                top = 4.dp,
                bottom = 16.dp,
                end = 20.dp
            ),
        text = title,
        style = FarmemeTheme.typography.body.small.semibold.copy(
            color = FarmemeTheme.textColor.tertiary
        )
    )
}

@Preview
@Composable
private fun MemeCategoryHeaderPreview(
    @PreviewParameter(provider = MemeCategoryHeaderProvider::class) header: String,
) {
    Box(modifier = Modifier.background(FarmemeTheme.backgroundColor.white)) {
        MemeCategoryHeader(title = header)
    }
}