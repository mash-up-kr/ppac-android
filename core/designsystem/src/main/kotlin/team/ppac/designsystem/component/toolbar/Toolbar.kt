package team.ppac.designsystem.component.toolbar

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import team.ppac.designsystem.FarmemeTheme
import team.ppac.designsystem.foundation.FarmemeIcon

@Composable
fun FarmemeActionToolBar(onClickActionIcon: () -> Unit) {
    FarmemeToolbar(actionIcons = {
        FarmemeIcon.Setting(
            modifier = Modifier
                .padding(12.dp)
                .clickable { onClickActionIcon() },
        )
    })
}

@Composable
fun FarmemeBackToolBar(title: String, onClickBackIcon: () -> Unit) {
    FarmemeToolbar(title = title, navigationIcon = {
        FarmemeIcon.Back(modifier = Modifier
            .padding(12.dp)
            .clickable { onClickBackIcon() })
    })
}

@Composable
fun FarmemeToolbar(
    modifier: Modifier = Modifier,
    title: String = "",
    navigationIcon: (@Composable () -> Unit)? = null,
    actionIcons: (@Composable () -> Unit)? = null,
) {
    Box(
        modifier = modifier
            .background(color = FarmemeTheme.backgroundColor.white)
            .fillMaxWidth()
            .height(50.dp)
            .statusBarsPadding(),
    ) {
        if (title.isNotEmpty()) {
            Row(
                modifier = Modifier.fillMaxSize(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(
                    text = title,
                    color = FarmemeTheme.textColor.primary,
                    style = FarmemeTheme.typography.body.xLarge.semibold,
                )
            }
        }
        if (navigationIcon != null) {
            Row(
                modifier = Modifier.fillMaxSize(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start,
            ) {
                navigationIcon()
            }
        }
        if (actionIcons != null) {
            Row(
                modifier = Modifier.fillMaxSize(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.End,
            ) {
                actionIcons()
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
