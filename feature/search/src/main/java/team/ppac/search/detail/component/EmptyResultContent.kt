package team.ppac.search.detail.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import team.ppac.designsystem.FarmemeTheme

@Composable
fun EmptyResultContent(
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Spacer(modifier = Modifier.size(160.dp))
        Column(
            modifier = Modifier.weight(1f),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                modifier = Modifier
                    .size(80.dp)
                    .background(Color.Red)
            )
            Spacer(modifier = Modifier.size(20.dp))
            Text(
                text = "밈이 없어요",
                style = FarmemeTheme.typography.heading.small.bold.copy(
                    color = FarmemeTheme.textColor.primary
                )
            )
            Spacer(modifier = Modifier.size(9.dp))
            Text(
                text = "카페인 빨리 충전하고\n재밌는 밈 채워둘게요!",
                style = FarmemeTheme.typography.body.large.medium.copy(
                    color = FarmemeTheme.textColor.tertiary
                )
            )
        }
        Spacer(modifier = Modifier.size(160.dp))
    }
}

@Preview
@Composable
private fun EmptyResultContentPreview() {
    Box(modifier = Modifier.background(Color.White)) {
        EmptyResultContent()
    }
}