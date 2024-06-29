package team.ppac.designsystem.component.toolbar

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import team.ppac.designsystem.FarmemeTheme
import team.ppac.designsystem.foundation.FarmemeIcon

@Composable
fun FarmemeActionToolBar(onClickActionIcon: () -> Unit) {
    FarmemeToolbar(
        actionIcon = {
            FarmemeIcon.Setting(
                modifier = Modifier
                    .clickable { onClickActionIcon() },
            )
        }
    )
}

@Composable
fun FarmemeBackToolBar(title: String, onClickBackIcon: () -> Unit) {
    FarmemeToolbar(
        title = title,
        navigationIcon = {
            FarmemeIcon.Back(
                modifier = Modifier
                    .clickable { onClickBackIcon() }
            )
        }
    )
}

@Composable
fun FarmemeToolbar(
    modifier: Modifier = Modifier,
    title: String = "",
    navigationIcon: (@Composable () -> Unit)? = null,
    actionIcon: (@Composable () -> Unit)? = null,
) {
    Row(
        modifier = modifier
            .background(color = FarmemeTheme.backgroundColor.white)
            .fillMaxWidth()
            .height(50.dp)
            .statusBarsPadding(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .padding(start = 20.dp)
                .padding(vertical = 15.dp)
                .size(24.dp)
        ) {
            if (navigationIcon != null) {
                navigationIcon()
            }
        }
        Text(
            modifier = Modifier.weight(1f),
            textAlign = TextAlign.Center,
            text = title,
            color = FarmemeTheme.textColor.primary,
            style = FarmemeTheme.typography.body.xLarge.semibold,
        )
        Box(
            modifier = Modifier
                .padding(end = 20.dp)
                .padding(vertical = 15.dp)
                .size(24.dp)
        ) {
            if (actionIcon != null) {
                actionIcon()
            }
        }
    }
}

@Preview
@Composable
fun PreviewBackTitleToolbar() {
    FarmemeBackToolBar(title = "키워드") {}
}

@Preview
@Composable
fun PreviewActionToolbar() {
    FarmemeActionToolBar {}
}
