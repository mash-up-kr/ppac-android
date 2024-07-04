package team.ppac.search.main.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Text
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import team.ppac.designsystem.FarmemeTheme
import team.ppac.designsystem.component.scaffold.FarmemeScaffold
import team.ppac.designsystem.foundation.FarmemeRadius
import team.ppac.designsystem.util.extension.noRippleClickable

@Composable
fun OpenServiceDialog(
    modifier: Modifier = Modifier,
    onConfirmClick: () -> Unit,
    onDismiss: () -> Unit,
) {
    Dialog(
        onDismissRequest = onDismiss,
        properties = DialogProperties()
    ) {
        Column(
            modifier = modifier
                .clip(FarmemeRadius.Radius20.shape)
                .fillMaxWidth()
                .background(FarmemeTheme.backgroundColor.white)
                .padding(
                    horizontal = 30.dp,
                    vertical = 20.dp
                )
        ) {
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = "조금만 기다려주세요!",
                style = FarmemeTheme.typography.heading.medium.semibold.copy(
                    color = FarmemeTheme.textColor.primary
                ),
            )
            Spacer(modifier = Modifier.size(8.dp))
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = "검색은 준비 중이에요.",
                style = FarmemeTheme.typography.body.large.medium.copy(
                    color = FarmemeTheme.textColor.secondary
                ),
            )
            Spacer(modifier = Modifier.size(14.dp))
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .noRippleClickable(onClick = onConfirmClick),
                text = "확인",
                style = FarmemeTheme.typography.heading.small.bold.copy(
                    color = FarmemeTheme.textColor.brand
                ),
                textAlign = TextAlign.End
            )
        }
    }
}

@Preview
@Composable
private fun OpenServiceDialogPreview() {
    FarmemeScaffold(
        scaffoldState = rememberScaffoldState()
    ) {
        OpenServiceDialog(
            onConfirmClick = {},
            onDismiss = {},
        )
    }
}