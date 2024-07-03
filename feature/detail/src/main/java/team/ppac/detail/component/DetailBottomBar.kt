package team.ppac.detail.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import team.ppac.designsystem.FarmemeTheme
import team.ppac.designsystem.component.tabbar.TabBar
import team.ppac.designsystem.foundation.FarmemeIcon

@Composable
internal fun DetailBottomBar() {
    TabBar(modifier = Modifier.fillMaxWidth()) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.CenterVertically)
                .padding(horizontal = 20.dp),
        ) {
            DetailBottomButton(
                icon = { FarmemeIcon.Copy(modifier = Modifier.size(20.dp)) },
                title = "복사",
                onClickButton = {}
            )
            DetailBottomButton(
                icon = { FarmemeIcon.Share(modifier = Modifier.size(20.dp)) },
                title = "공유",
                onClickButton = {}
            )
            DetailBottomButton(
                icon = { FarmemeIcon.BookmarkLine(modifier = Modifier.size(20.dp)) },
                title = "북마크",
                onClickButton = {}
            )
        }
    }
}

@Composable
internal fun RowScope.DetailBottomButton(
    icon: @Composable () -> Unit,
    title: String,
    onClickButton: () -> Unit,
) {
    Row(
        modifier = Modifier
            .weight(1f)
            .clickable(onClick = onClickButton)
            .padding(vertical = 15.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center,
    ) {
        icon()
        Spacer(modifier = Modifier.width(4.dp))
        Text(
            text = title,
            style = FarmemeTheme.typography.body.xLarge.semibold,
            color = FarmemeTheme.textColor.primary,
        )
    }
}

@Composable
@Preview(showBackground = true)
fun PreviewDetailBottomBar() {
    DetailBottomBar()
}