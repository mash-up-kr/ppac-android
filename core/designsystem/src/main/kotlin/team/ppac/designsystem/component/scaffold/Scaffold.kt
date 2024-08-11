package team.ppac.designsystem.component.scaffold

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
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
import team.ppac.designsystem.FarmemeTheme
import team.ppac.designsystem.component.scaffold.type.BackgroundColorType
import team.ppac.designsystem.component.scaffold.type.defaultColorType
import team.ppac.designsystem.foundation.ContentMargin

@Composable
fun FarmemeScaffold(
    modifier: Modifier = Modifier,
    isIncludeHorizontalPadding: Boolean = true,
    backgroundColorType: BackgroundColorType = defaultColorType(),
    backgroundImage: @Composable () -> Unit = {},
    topBar: @Composable () -> Unit = {},
    bottomBar: @Composable () -> Unit = {},
    content: @Composable (PaddingValues) -> Unit,
) {
    val backgroundModifier = when (backgroundColorType) {
        is BackgroundColorType.GradientColor -> {
            Modifier.background(brush = backgroundColorType.brush)
        }

        is BackgroundColorType.SolidColor -> {
            Modifier.background(color = backgroundColorType.color)
        }
    }
    Box(modifier = Modifier.fillMaxSize()) {
        backgroundImage()
        Column(
            modifier = modifier.then(backgroundModifier),
        ) {
            Box {
                backgroundImage()
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    backgroundColor = Color.Transparent,
                    topBar = topBar,
                    bottomBar = bottomBar,
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
        backgroundColorType = BackgroundColorType.GradientColor(FarmemeTheme.backgroundColor.brandLemonGradient),
    ) {
        Column(
            modifier = Modifier.padding(it)
        ) {
            Text(text = "파밈파밈파밈파밈")
        }
    }
}
