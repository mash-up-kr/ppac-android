package team.ppac.detail.component

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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import team.ppac.common.android.util.shareOneLink
import team.ppac.designsystem.FarmemeTheme
import team.ppac.designsystem.component.tabbar.TabBar
import team.ppac.designsystem.foundation.FarmemeIcon
import team.ppac.designsystem.util.extension.noRippleClickable

@Composable
internal fun DetailBottomBar(memeId: String) {
    val context = LocalContext.current
    TabBar(modifier = Modifier.fillMaxWidth()) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.CenterVertically)
                .padding(horizontal = 20.dp),
        ) {
            DetailBottomButton(
                title = "복사",
                onClickButton = {},
            ) {
                FarmemeIcon.Copy(modifier = Modifier.size(20.dp))
            }
            DetailBottomButton(
                title = "공유",
                onClickButton = { context.shareOneLink(memeId) },
            ) {
                FarmemeIcon.Share(modifier = Modifier.size(20.dp))
            }
            DetailBottomButton(
                title = "북마크",
                onClickButton = {},
            ) {
                FarmemeIcon.BookmarkLine(modifier = Modifier.size(20.dp))
            }
        }
    }
}

@Composable
internal fun RowScope.DetailBottomButton(
    title: String,
    onClickButton: () -> Unit,
    icon: @Composable () -> Unit,
) {
    Row(
        modifier = Modifier
            .weight(1f)
            .noRippleClickable(onClick = onClickButton)
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
    DetailBottomBar(memeId = "")
}