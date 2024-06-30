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

@Composable
fun MyPageLevel(
    modifier: Modifier = Modifier,
    title: String,
    count: Int,
    step: Int,
) {
    Column(
        modifier = modifier
            .clip(FarmemeRadius.Radius20.shape)
            .border(
                width = 2.dp,
                color = FarmemeTheme.iconColor.disabled,
                shape = FarmemeRadius.Radius20.shape,
            )
    ) {
        MyPageLevelTop(
            title = title,
            count = count,
        )
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(2.dp)
                .background(FarmemeTheme.borderColor.secondary)
        )
        MyPageLevelBottom(step = step)
    }
}

@Composable
fun MyPageLevelTop(
    modifier: Modifier = Modifier,
    title: String,
    count: Int,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(FarmemeTheme.backgroundColor.assistive)
            .padding(start = 20.dp, top = 16.dp, end = 20.dp, bottom = 20.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        MyPageLevelTitle(title = title)
        MyPageLevelChip(count = count)
    }
}

@Composable
fun MyPageLevelTitle(
    modifier: Modifier = Modifier,
    title: String,
) {
    Column(
        modifier = modifier,
    ) {
        Text(
            text = "다음 레벨 달성 조건",
            color = FarmemeTheme.textColor.tertiary,
            textAlign = TextAlign.Start,
            style = FarmemeTheme.typography.body.medium.semibold,
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = title,
            color = FarmemeTheme.textColor.primary,
            textAlign = TextAlign.Start,
            style = FarmemeTheme.typography.heading.medium.bold,
        )
    }
}

@Composable
fun MyPageLevelBottom(
    modifier: Modifier = Modifier,
    step: Int,
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .background(FarmemeTheme.backgroundColor.white)
            .padding(start = 20.dp, top = 20.dp, end = 20.dp, bottom = 30.dp),
    ) {
        MyPageLevelStep(step = step)
    }
}


@Preview
@Composable
fun MyPageLevelPreview() {
    MyPageLevel(
        title = "밈 20번 보기",
        count = 1,
        step = 2
    )
}