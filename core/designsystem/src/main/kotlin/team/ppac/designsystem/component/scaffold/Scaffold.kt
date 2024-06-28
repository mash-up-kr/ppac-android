package team.ppac.designsystem.component.scaffold

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.ScaffoldState
import androidx.compose.material.Text
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import team.ppac.designsystem.FarmemeTheme
import team.ppac.designsystem.foundation.ContentMargin

@Composable
fun FarmemeScaffold(
    modifier: Modifier = Modifier,
    isIncludeHorizontalPadding: Boolean = true,
    isGradientBackgroundColor: Boolean = true,
    backgroundColor: Color? = null,
    scaffoldState: ScaffoldState,
    topBar: @Composable () -> Unit = {},
    bottomBar: @Composable () -> Unit = {},
    content: @Composable (PaddingValues) -> Unit,
) {
    val backgroundModifier = if (isGradientBackgroundColor) {
        modifier.background(FarmemeTheme.backgroundColor.brandLemonGradient)
    } else {
        backgroundColor?.let { color ->
            modifier.background(
                brush = Brush.horizontalGradient(listOf(color, color))
            )
        }
    } ?: modifier

    Column(
        modifier = modifier.then(backgroundModifier),
    ) {
        Scaffold(
            modifier = Modifier.fillMaxSize(),
            backgroundColor = Color.Transparent,
            topBar = topBar,
            bottomBar = bottomBar,
            scaffoldState = scaffoldState,
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
        },
//        backgroundColor = FarmemeTheme.backgroundColor.brand
        isGradientBackgroundColor = true,
        scaffoldState = rememberScaffoldState()
    ) {
        Column(
            modifier = Modifier.padding(it)
        ) {
            Text(text = "파밈파밈파밈파밈")
        }
    }
}
