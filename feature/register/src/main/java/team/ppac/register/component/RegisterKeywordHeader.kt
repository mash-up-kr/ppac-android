@file:JvmName("RegisterKeywordHeaderKt")

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
internal fun RegisterKeywordHeader(
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier,
    ) {
        Spacer(modifier = Modifier.height(35.dp))
        RegisterListHeader(title = "연관있는 키워드를 골라주세요")
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp),
            text = "최대 6개까지 선택 가능해요",
            style = FarmemeTheme.typography.body.medium.medium.copy(
                color = FarmemeTheme.textColor.secondary,
            ),
        )
        Spacer(modifier = Modifier.height(24.dp))
    }
}

@Preview(showBackground = true)
@Composable
private fun RegisterKeywordHeaderPreview() {
    RegisterKeywordHeader()
}