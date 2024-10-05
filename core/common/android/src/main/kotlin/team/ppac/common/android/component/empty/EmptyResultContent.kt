package team.ppac.common.android.component.empty

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import team.ppac.designsystem.FarmemeTheme

@Composable
fun EmptyResultContent(
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                modifier = Modifier.size(80.dp),
                painter = painterResource(id = team.ppac.designsystem.R.drawable.img_empty),
                contentDescription = null,
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
    }
}

@Preview
@Composable
private fun EmptyResultContentPreview() {
    Box(modifier = Modifier.background(Color.White)) {
        EmptyResultContent()
    }
}