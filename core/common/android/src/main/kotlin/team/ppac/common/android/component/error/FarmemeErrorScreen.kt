package team.ppac.common.android.component.error

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import team.ppac.designsystem.FarmemeTheme
import team.ppac.designsystem.component.button.FarmemeFilledButton
import team.ppac.designsystem.foundation.FarmemeIcon

@Composable
fun FarmemeErrorScreen(
    modifier: Modifier = Modifier,
    title: String,
    onRetryClick: () -> Unit,
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        FarmemeIcon.Caution()
        Spacer(modifier = Modifier.size(20.dp))
        Text(
            text = title,
            style = FarmemeTheme.typography.body.large.medium.copy(
                color = FarmemeTheme.textColor.primary
            ),
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.size(24.dp))
        FarmemeFilledButton(
            backgroundColor = FarmemeTheme.backgroundColor.primary,
            text = "새로고침하기",
            textColor = FarmemeTheme.textColor.inverse,
            onClick = onRetryClick
        )
    }
}

@Preview
@Composable
private fun FarmemeErrorScreenPreview() {
    FarmemeErrorScreen(
        modifier = Modifier.fillMaxSize(),
        title = "정보를 불러오지 못 했어요.\n 새로고침 해주세요.",
        onRetryClick = { }
    )
}