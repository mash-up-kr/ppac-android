package team.ppac.designsystem.component.scaffold

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import team.ppac.designsystem.foundation.ContentMargin
import team.ppac.designsystem.foundation.FarmemeTheme

@Composable
fun FarmemeScaffold(
    modifier: Modifier = Modifier,
    isIncludeHorizontalPadding: Boolean = true,
    backgroundColor: Color = FarmemeTheme.backgroundColor.brand,
    topBar: @Composable () -> Unit = {},
    bottomBar: @Composable () -> Unit = {},
    content: @Composable (PaddingValues) -> Unit,
) {
    Scaffold(
        modifier = modifier.fillMaxSize(),
        backgroundColor = backgroundColor,
        topBar = topBar,
        bottomBar = bottomBar
    ) { paddingValues ->
        val innerPadding = PaddingValues(
            top = paddingValues.calculateTopPadding(),
            start = if (isIncludeHorizontalPadding) ContentMargin else 0.dp,
            end = if (isIncludeHorizontalPadding) ContentMargin else 0.dp,
            bottom = paddingValues.calculateBottomPadding()
        )

        content(innerPadding)
    }
}

@Preview
@Composable
private fun FarmemeScaffoldPreview() {
    FarmemeScaffold(
        topBar = {
            Text(text = "파밈파밈파밈파밈")
        },
        bottomBar = {
            Text(text = "파밈파밈파밈파밈")
        }
    ) {
        Column(
            modifier = Modifier.padding(it)
        ) {
            Text(text = "파밈파밈파밈파밈")
        }
    }
}
