package team.ppac.designsystem.component.tabbar

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.WindowInsetsSides
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.only
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.material.Surface
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import team.ppac.designsystem.FarmemeTheme
import team.ppac.designsystem.component.scaffold.FarmemeScaffold
import team.ppac.designsystem.component.scaffold.type.BackgroundColorType
import team.ppac.designsystem.foundation.FarmemeRadius
import team.ppac.designsystem.util.extension.boxShadow

@Composable
fun FarmemeNavigationBar(
    modifier: Modifier = Modifier,
    content: @Composable RowScope.() -> Unit,
) {
    TabBar(
        modifier = modifier,
        windowInsets = WindowInsets.systemBars.only(WindowInsetsSides.Horizontal + WindowInsetsSides.Bottom),
        content = content
    )
}

internal val TabBarHeight = 64.dp

@Composable
fun TabBar(
    modifier: Modifier = Modifier,
    backgroundColor: Color = FarmemeTheme.backgroundColor.white,
    windowInsets: WindowInsets = WindowInsets.systemBars.only(WindowInsetsSides.Horizontal + WindowInsetsSides.Bottom),
    content: @Composable RowScope.() -> Unit,
) {
    Surface(
        modifier = modifier
            .background(color = Color(0XFFFCFCFC), shape = FarmemeRadius.Radius30.shape)
            .boxShadow(
                color = Color(0X0D000000),  // TODO(JaesungLeee) : 컬러 재정의 필요
                blurRadius = 20.dp,
                offset = DpOffset(x = 0.dp, y = (-4).dp)
            )
            .clip(FarmemeRadius.RadiusTop30.shape),
        color = backgroundColor,
    ) {
        Row(
            Modifier
                .fillMaxWidth()
                .windowInsetsPadding(windowInsets)
                .defaultMinSize(minHeight = TabBarHeight)
                .selectableGroup(),
            horizontalArrangement = Arrangement.SpaceBetween,
            content = content
        )
    }
}


@Preview
@Composable
private fun FarmemeTabBarPreview() {
    FarmemeScaffold(
        bottomBar = {
            TabBar() {}
        },
        scaffoldState = rememberScaffoldState(),
        backgroundColorType = BackgroundColorType.GradientColor(FarmemeTheme.backgroundColor.brandLemonGradient),
        content = {},
    )
}