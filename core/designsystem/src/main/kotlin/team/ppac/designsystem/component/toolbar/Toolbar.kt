@file:OptIn(ExperimentalLayoutApi::class)

package team.ppac.designsystem.component.toolbar

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsIgnoringVisibility
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
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
    Column(modifier = modifier) {
        Row(
            modifier = Modifier
                .padding(
                    top = with(LocalDensity.current) {
                        WindowInsets.statusBarsIgnoringVisibility
                            .getTop(this)
                            .toDp()
                    }
                )
                .fillMaxWidth()
                .height(50.dp),
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
        Divider(
            modifier = Modifier.fillMaxWidth(),
            color = FarmemeTheme.backgroundColor.assistive,
            thickness = 1.dp,
        )
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
