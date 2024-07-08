package team.ppac.mypage.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import team.ppac.designsystem.FarmemeTheme
import team.ppac.designsystem.foundation.FarmemeRadius
import team.ppac.mypage.model.LeveInfo
import team.ppac.mypage.model.MyPageLevel

@Composable
internal fun MyPageLevelBox(
    modifier: Modifier = Modifier,
    leveInfo: LeveInfo,
) {
    Column(
        modifier = modifier
            .clip(FarmemeRadius.Radius20.shape)
            .border(
                width = 2.dp,
                color = FarmemeTheme.borderColor.tertiary,
                shape = FarmemeRadius.Radius20.shape,
            )
    ) {
        MyPageLevelTop(
            leveInfo = leveInfo,
        )
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(2.dp)
                .background(FarmemeTheme.borderColor.tertiary)
        )
        MyPageLevelBottom(leveInfo = leveInfo)
    }
}

@Composable
private fun MyPageLevelTop(
    modifier: Modifier = Modifier,
    leveInfo: LeveInfo,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(FarmemeTheme.backgroundColor.assistive)
            .padding(
                start = 20.dp,
                top = 16.dp,
                end = 20.dp,
                bottom = 20.dp,
            ),
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        MyPageLevelTitle(leveInfo = leveInfo)
        MyPageLevelChip(memeCount = leveInfo.memeCount)
    }
}

@Composable
private fun MyPageLevelTitle(
    modifier: Modifier = Modifier,
    leveInfo: LeveInfo,
) {
    Column(
        modifier = modifier,
    ) {
        Text(
            text = when (leveInfo.userLevel) {
                MyPageLevel.LEVEL4 -> "최종 레벨 달성 조건"
                else -> "다음 레벨 달성 조건"
            },
            color = FarmemeTheme.textColor.tertiary,
            style = FarmemeTheme.typography.body.medium.semibold,
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = leveInfo.userLevel.stepTitle,
            color = FarmemeTheme.textColor.primary,
            style = FarmemeTheme.typography.heading.small.semibold,
        )
    }
}

@Composable
private fun MyPageLevelBottom(
    modifier: Modifier = Modifier,
    leveInfo: LeveInfo,
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .background(FarmemeTheme.backgroundColor.white)
            .padding(start = 20.dp, top = 20.dp, end = 20.dp, bottom = 30.dp),
    ) {
        MyPageLevelStep(leveInfo = leveInfo)
    }
}

@Preview
@Composable
private fun MyPageLevelPreview() {
    MyPageLevelBox(
        leveInfo = LeveInfo(
            userLevel = MyPageLevel.LEVEL3,
            memeCount = 15,
        )
    )
}