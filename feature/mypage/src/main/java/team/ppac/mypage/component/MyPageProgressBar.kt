package team.ppac.mypage.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import team.ppac.designsystem.FarmemeTheme
import team.ppac.designsystem.foundation.FarmemeIcon
import team.ppac.designsystem.foundation.FarmemeRadius
import team.ppac.mypage.model.LeveInfo
import team.ppac.mypage.model.MyPageLevel

@Composable
internal fun MyPageProgressBar(
    modifier: Modifier = Modifier,
    leveInfo: LeveInfo,
) {
    BoxWithConstraints(
        modifier = modifier,
    ) {
        MyPageProgressBarBackground()
        MyPageProgressBarActive(
            maxWidth = maxWidth,
            leveInfo = leveInfo,
        )
    }
}

@Composable
private fun MyPageProgressBarActive(
    modifier: Modifier = Modifier,
    maxWidth: Dp,
    leveInfo: LeveInfo,
) {
    val minWidth = 96.dp

    Row(
        modifier = modifier
            .width(minWidth + (maxWidth - minWidth) * 0.05f * leveInfo.memeCount)
            .height(44.dp)
            .border(
                width = 2.dp,
                color = FarmemeTheme.borderColor.primary,
                shape = FarmemeRadius.Radius40.shape,
            )
            .background(
                color = FarmemeTheme.backgroundColor.brand,
                shape = FarmemeRadius.Radius40.shape,
            )
            .padding(
                start = 12.dp,
                end = 20.dp,
            ),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start,
    ) {
        when (leveInfo.userLevel) {
            MyPageLevel.LEVEL1 -> FarmemeIcon.Level1(modifier = Modifier.size(24.dp))
            MyPageLevel.LEVEL2 -> FarmemeIcon.Level2(modifier = Modifier.size(24.dp))
            MyPageLevel.LEVEL3 -> FarmemeIcon.Level3(modifier = Modifier.size(24.dp))
            MyPageLevel.LEVEL4 -> FarmemeIcon.Level4(modifier = Modifier.size(24.dp))
        }
        Spacer(Modifier.width(4.dp))
        Text(
            text = "LV. ${leveInfo.userLevel.level}",
            style = FarmemeTheme.typography.body.xLarge.semibold,
            color = FarmemeTheme.textColor.inverse,
        )
    }
}

@Composable
private fun MyPageProgressBarBackground(
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(44.dp)
            .border(
                width = 2.dp,
                color = FarmemeTheme.borderColor.tertiary,
                shape = FarmemeRadius.Radius40.shape,
            )
            .background(
                color = FarmemeTheme.backgroundColor.assistive,
                shape = FarmemeRadius.Radius40.shape,
            )
    )
}

@Preview
@Composable
private fun MyPageProgressBarPreview() {
    MyPageProgressBar(
        leveInfo = LeveInfo(
            userLevel = MyPageLevel.LEVEL3,
            memeCount = 15,
        )
    )
}