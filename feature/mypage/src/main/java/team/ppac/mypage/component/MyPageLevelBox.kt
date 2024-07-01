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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import team.ppac.designsystem.FarmemeTheme
import team.ppac.designsystem.foundation.FarmemeRadius
import team.ppac.mypage.model.MyPageLevel

@Composable
internal fun MyPageLevelBox(
    modifier: Modifier = Modifier,
    level: MyPageLevel,
    count: Int,
) {
    Column(
        modifier = modifier
            .padding(start = 20.dp, top = 16.dp, end = 20.dp, bottom = 40.dp)
            .clip(FarmemeRadius.Radius20.shape)
            .border(
                width = 2.dp,
                color = FarmemeTheme.iconColor.disabled,
                shape = FarmemeRadius.Radius20.shape,
            )
    ) {
        MyPageLevelTop(
            count = count,
            level = level,
        )
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(2.dp)
                .background(FarmemeTheme.borderColor.secondary)
        )
        MyPageLevelBottom(level = level, count = count)
    }
}

@Composable
private fun MyPageLevelTop(
    modifier: Modifier = Modifier,
    level: MyPageLevel,
    count: Int,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(FarmemeTheme.backgroundColor.assistive)
            .padding(start = 20.dp, top = 16.dp, end = 20.dp, bottom = 20.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        MyPageLevelTitle(level = level)
        MyPageLevelChip(count = count)
    }
}

@Composable
private fun MyPageLevelTitle(
    modifier: Modifier = Modifier,
    level: MyPageLevel,
) {
    Column(
        modifier = modifier,
    ) {
        Text(
            text = when (level) {
                MyPageLevel.LEVEL4 -> "최종 레벨 달성 조건"
                else -> "다음 레벨 달성 조건"
            },
            color = FarmemeTheme.textColor.tertiary,
            textAlign = TextAlign.Start,
            style = FarmemeTheme.typography.body.medium.semibold,
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = level.stepTitle,
            color = FarmemeTheme.textColor.primary,
            textAlign = TextAlign.Start,
            style = FarmemeTheme.typography.heading.small.semibold,
        )
    }
}

@Composable
private fun MyPageLevelBottom(
    modifier: Modifier = Modifier,
    count: Int,
    level: MyPageLevel,
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .background(FarmemeTheme.backgroundColor.white)
            .padding(start = 20.dp, top = 20.dp, end = 20.dp, bottom = 30.dp),
    ) {
        MyPageLevelStep(level = level, count = count)
    }
}

@Preview
@Composable
private fun MyPageLevelPreview() {
    MyPageLevelBox(
        level = MyPageLevel.LEVEL3,
        count = 15,
    )
}