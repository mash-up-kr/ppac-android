package team.ppac.mypage.component

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import team.ppac.designsystem.FarmemeTheme
import team.ppac.designsystem.component.chip.FarmemeSmallChip
import team.ppac.designsystem.foundation.FarmemeIcon

@Composable
fun MyPageLevelStep(
    modifier: Modifier = Modifier,
    step: Int,
) {
    Column(
        modifier = modifier.fillMaxWidth(),
    ) {
        MyPageLevelStepProgress(step = step)
        Spacer(modifier = Modifier.height(10.dp))
        MyPageStepChips(step = step)
    }
}

@Composable
fun MyPageLevelStepProgress(
    modifier: Modifier = Modifier,
    step: Int,
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 20.5.dp),
        contentAlignment = Alignment.Center,
    ) {
        MyPageDottedLines(step = step)
        MyPageStepIcons(step = step)
    }
}

@Composable
fun MyPageDottedLines(
    modifier: Modifier = Modifier,
    step: Int,
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        MyPageDottedLine(
            modifier = Modifier
                .weight(1.0f)
                .padding(start = 12.dp),
            enabled = step > 1,
        )
        MyPageDottedLine(
            modifier = Modifier
                .weight(1.0f),
            enabled = step > 2,
        )
        MyPageDottedLine(
            modifier = Modifier
                .weight(1.0f)
                .padding(end = 12.dp),
            enabled = step > 3,
        )
    }
}

@Composable
fun MyPageDottedLine(
    modifier: Modifier = Modifier,
    enabled: Boolean = false,
) {
    val iconColor = if (enabled) FarmemeTheme.iconColor.brand else FarmemeTheme.iconColor.assistive

    Canvas(modifier = modifier) {
        drawLine(
            color = iconColor,
            start = Offset(0f, 0f),
            end = Offset(size.width, 0f),
            strokeWidth = 4f,
            pathEffect = PathEffect.dashPathEffect(floatArrayOf(10f, 10f), 10f),
            cap = StrokeCap.Round,
        )
    }
}

@Composable
fun MyPageStepIcons(
    modifier: Modifier = Modifier,
    step: Int,
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
    ) {

        for (i in 0 until 4) {
            when {
                i < step -> FarmemeIcon.LevelCheck()
                i == step -> FarmemeIcon.LevelCurrent()
                else -> FarmemeIcon.LevelDisabled()
            }
        }
    }
}

@Composable
fun MyPageStepChips(
    modifier: Modifier = Modifier,
    step: Int,
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        FarmemeSmallChip(text = "밈 보기", enabled = step > 0)
        FarmemeSmallChip(text = "ㅋ 남기기", enabled = step > 1)
        FarmemeSmallChip(text = "밈 공유", enabled = step > 2)
        FarmemeSmallChip(text = "밈 저장", enabled = step > 3)
    }
}

@Preview
@Composable
fun MyPageLevelStepPreview() {
    MyPageLevelStep(step = 2)
}