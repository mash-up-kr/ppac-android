package team.ppac.mypage.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import team.ppac.designsystem.FarmemeTheme
import team.ppac.designsystem.util.extension.noRippleClickable
import team.ppac.mypage.mvi.MyPageTab

@Composable
internal fun MyPageMemesTabBar(
    modifier: Modifier = Modifier,
    currentTab: MyPageTab,
    onClick: (MyPageTab) -> Unit,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp)
    ) {
        MyPageMemesTab(
            modifier = Modifier.weight(1f),
            currentTab = MyPageTab.MY_MEMES,
            isSelected = currentTab == MyPageTab.MY_MEMES,
            onClick = onClick,
        )
        Spacer(modifier = Modifier.width(8.dp))
        MyPageMemesTab(
            modifier = Modifier.weight(1f),
            currentTab = MyPageTab.SAVED_MEMES,
            isSelected = currentTab == MyPageTab.SAVED_MEMES,
            onClick = onClick,
        )
    }
}

@Composable
internal fun MyPageMemesTab(
    modifier: Modifier = Modifier,
    currentTab: MyPageTab,
    isSelected: Boolean,
    onClick: (MyPageTab) -> Unit,
) {
    val textColor =
        if (isSelected) FarmemeTheme.textColor.primary else FarmemeTheme.textColor.tertiary
    val lineColor = if (isSelected) FarmemeTheme.backgroundColor.primary else Color.Unspecified

    Column(
        modifier = modifier.noRippleClickable(onClick = {
            onClick(currentTab)
        }),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Spacer(modifier = Modifier.height(10.dp))
        Text(
            text = currentTab.title,
            color = textColor,
            style = FarmemeTheme.typography.body.xLarge.semibold,
        )
        Spacer(modifier = Modifier.height(15.dp))
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(2.dp)
                .background(lineColor),
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun MyPageMemesTabBarPreview(
    modifier: Modifier = Modifier,
) {
    MyPageMemesTabBar(
        currentTab = MyPageTab.MY_MEMES,
        onClick = {},
    )
}

@Preview(showBackground = true)
@Composable
private fun MyPageMemesTabPreview(
    modifier: Modifier = Modifier,
) {
    Column {
        MyPageMemesTab(
            currentTab = MyPageTab.MY_MEMES,
            isSelected = true,
            onClick = {},
        )
        MyPageMemesTab(
            currentTab = MyPageTab.SAVED_MEMES,
            isSelected = false,
            onClick = {},
        )
    }
}