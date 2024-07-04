package team.ppac.setting.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
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
import team.ppac.designsystem.util.extension.noRippleClickable

@Composable
internal fun SettingListItem(
    modifier: Modifier = Modifier,
    title: String,
    onClick: () -> Unit,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .noRippleClickable(onClick = onClick)
            .padding(all = 20.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(
            text = title,
            color = FarmemeTheme.textColor.primary,
            style = FarmemeTheme.typography.body.xLarge.semibold,
        )
        FarmemeIcon.ArrowRight(
            modifier = Modifier.size(16.dp),
            tint = FarmemeTheme.iconColor.assistive,
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun SettingListItemPreview() {
    SettingListItem(
        title = "개인정보 처리방침",
        onClick = { },
    )
}