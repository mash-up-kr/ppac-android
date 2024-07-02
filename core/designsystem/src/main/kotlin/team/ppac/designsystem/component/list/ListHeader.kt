package team.ppac.designsystem.component.list

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import team.ppac.designsystem.FarmemeTheme
import team.ppac.designsystem.foundation.FarmemeIcon

@Composable
fun FarmemeListHeader(
    modifier: Modifier = Modifier,
    title: String,
    leadingIcon: @Composable (() -> Unit)? = null,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp, vertical = 18.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        leadingIcon?.let { icon ->
            icon()
            Spacer(modifier = Modifier.size(8.dp))
        }

        Text(
            modifier = Modifier.fillMaxWidth(),
            text = title,
            style = FarmemeTheme.typography.heading.small.semibold.copy(
                color = FarmemeTheme.textColor.primary
            )
        )
    }
}

@Preview
@Composable
private fun FarmemeListHeaderPreview() {
    Column(modifier = Modifier.background(FarmemeTheme.backgroundColor.brandLemonGradient)) {
        FarmemeListHeader(
            title = "text",
            leadingIcon = {
                FarmemeIcon.Search(Modifier.size(20.dp))
            }
        )

        FarmemeListHeader(
            title = "두둥! 요즘 핫한 #키워드",
            leadingIcon = {
                FarmemeIcon.Special(Modifier.size(20.dp))
            }
        )
    }
}