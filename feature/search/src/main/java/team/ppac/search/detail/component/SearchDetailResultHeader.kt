package team.ppac.search.detail.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import team.ppac.designsystem.FarmemeTheme

@Composable
internal fun SearchDetailResultHeader(
    modifier: Modifier = Modifier,
    totalCount: Int,
) {
    Text(
        modifier = modifier
            .fillMaxWidth()
            .background(FarmemeTheme.backgroundColor.white)
            .padding(vertical = 20.dp),
        text = "${totalCount}개의 밈을 찾았어요.",
        style = FarmemeTheme.typography.body.medium.medium.copy(
            color = FarmemeTheme.textColor.primary
        )
    )
}

@Preview
@Composable
private fun SearchDetailResultHeaderPreview() {
    SearchDetailResultHeader(totalCount = 10)
}