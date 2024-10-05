package team.ppac.common.android.component.paging

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import team.ppac.designsystem.FarmemeTheme

@Composable
fun SearchDetailResultFooter(
    modifier: Modifier = Modifier,
) {
    Text(
        modifier = modifier
            .padding(vertical = 100.dp)
            .fillMaxWidth(),
        text = "카페인 빨리 충전하고\n재밌는 밈 더 찾아둘게요!",
        style = FarmemeTheme.typography.body.large.medium.copy(
            color = FarmemeTheme.textColor.assistive
        ),
        textAlign = TextAlign.Center
    )
}

@Preview
@Composable
private fun SearchDetailResultFooterPreview() {
    Box(modifier = Modifier.background(Color.White)) {
        SearchDetailResultFooter()
    }
}
