package team.ppac.register.component

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import team.ppac.designsystem.FarmemeTheme
import team.ppac.designsystem.foundation.FarmemeIcon

@Composable
internal fun RegisterListHeader(
    modifier: Modifier = Modifier,
    title: String,
) {
    Row(
        modifier = modifier.fillMaxWidth(),
    ) {
        Text(
            text = title,
            style = FarmemeTheme.typography.body.xLarge.semibold.copy(
                color = FarmemeTheme.textColor.primary,
            ),
        )
        Spacer(modifier = Modifier.width(4.dp))
        FarmemeIcon.Required()
    }
}

@Preview(showBackground = true)
@Composable
private fun RegisterListHeaderPreview() {
    RegisterListHeader(
        title = "밈의 제목"
    )
}