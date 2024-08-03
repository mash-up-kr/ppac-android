@file:OptIn(ExperimentalLayoutApi::class)

package team.ppac.designsystem.component.toolbar

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
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
import team.ppac.designsystem.util.extension.noRippleClickable

@Composable
fun FarmemeActionToolBar(onClickActionIcon: () -> Unit) {
    FarmemeToolbar(
        actionIcon = {
            FarmemeIcon.Setting(
                modifier = Modifier
                    .size(20.dp)
                    .noRippleClickable(onClick = onClickActionIcon)
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
                    .size(20.dp)
                    .noRippleClickable(onClick = onClickBackIcon)
            )
        }
    )
}

@Composable
internal fun FarmemeToolbar(
    modifier: Modifier = Modifier,
    title: String = "",
    navigationIcon: (@Composable () -> Unit)? = null,
    actionIcon: (@Composable () -> Unit)? = null,
) {
    Row(
        modifier = modifier
            .padding(
                horizontal = 20.dp,
                vertical = 15.dp
            )
            .statusBarsPadding()
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier.size(20.dp)
        ) {
            navigationIcon?.invoke()
        }
        Spacer(modifier = Modifier.size(12.dp))
        Text(
            modifier = Modifier.weight(1f),
            textAlign = TextAlign.Center,
            text = title,
            color = FarmemeTheme.textColor.primary,
            style = FarmemeTheme.typography.body.xLarge.semibold,
        )
        Spacer(modifier = Modifier.size(12.dp))
        Box(
            modifier = Modifier.size(20.dp)
        ) {
            actionIcon?.invoke()
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewBackTitleToolbar() {
    FarmemeBackToolBar(title = "키워드") {}
}

@Preview(showBackground = true)
@Composable
fun PreviewActionToolbar() {
    FarmemeActionToolBar {}
}
