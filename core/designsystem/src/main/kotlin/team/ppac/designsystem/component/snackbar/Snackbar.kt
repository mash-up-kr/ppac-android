package team.ppac.designsystem.component.snackbar

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import team.ppac.designsystem.FarmemeTheme
import team.ppac.designsystem.foundation.FarmemeIcon
import team.ppac.designsystem.foundation.FarmemeRadius

@Composable
fun FarmemeSnackbar(
    modifier: Modifier = Modifier,
    message: String,
    leadingIcon: @Composable (() -> Unit)? = null,
) {
    Row(
        modifier = modifier
            .wrapContentWidth()
            .background(
                color = FarmemeTheme.backgroundColor.dimmer2,
                shape = FarmemeRadius.Radius25.shape
            )
            .padding(horizontal = 20.dp, vertical = 16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        leadingIcon?.let { icon ->
            icon()
            Spacer(modifier = Modifier.size(8.dp))
        }
        Text(
            text = message,
            style = FarmemeTheme.typography.body.large.semibold.copy(
                color = FarmemeTheme.textColor.inverse
            )
        )
    }
}

@Preview
@Composable
private fun FarmemeSnackbarPreview() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        FarmemeSnackbar(
            message = "파밈을 취소했어요"
        )

        FarmemeSnackbar(
            message = "파밈 완료!",
            leadingIcon = {
                FarmemeIcon.BookmarkFilled(
                    modifier = Modifier.size(20.dp)
                )
            }
        )
    }
}