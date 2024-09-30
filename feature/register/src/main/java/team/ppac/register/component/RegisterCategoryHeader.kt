package team.ppac.register.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import team.ppac.designsystem.FarmemeTheme

@Composable
internal fun RegisterCategoryHeader(
    modifier: Modifier = Modifier,
    title: String,
) {
    Column {
        Text(
            modifier = modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp),
            text = title,
            style = FarmemeTheme.typography.body.small.semibold.copy(
                color = FarmemeTheme.textColor.tertiary
            ),
        )
        Spacer(modifier = Modifier.height(16.dp))
    }
}

@Preview(showBackground = true)
@Composable
private fun RegisterCategoryHeaderPreview() {
    RegisterCategoryHeader(title = "카테고리")
}