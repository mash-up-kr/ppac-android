package team.ppac.mypage.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import team.ppac.designsystem.component.chip.FarmemeSmallChip
import team.ppac.designsystem.foundation.FarmemeIcon

@Composable
fun MyPageLevelStep(
    modifier: Modifier = Modifier,
    step: Int,
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        MyPageLevelStepItem(
            title = "밈 보기",
            icon = { FarmemeIcon.LevelCheck() },
            enabled = (step > 0),
        )
        MyPageLevelStepItem(
            title = "ㅋ 남기기",
            icon = { FarmemeIcon.LevelCurrent() },
            enabled = (step > 1),
        )
        MyPageLevelStepItem(
            title = "밈 공유",
            icon = { FarmemeIcon.LevelDisabled() },
            enabled = (step > 2),
        )
        MyPageLevelStepItem(
            title = "밈 저장",
            icon = { FarmemeIcon.LevelDisabled() },
            enabled = (step > 3),
        )
    }
}

@Composable
fun MyPageLevelStepItem(
    modifier: Modifier = Modifier,
    title: String,
    icon: @Composable () -> Unit,
    enabled: Boolean = false,
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        icon()
        Spacer(modifier = Modifier.height(10.dp))
        FarmemeSmallChip(text = title, enabled = enabled)
    }
}

@Preview
@Composable
fun MyPageLevelStepPreview() {
    MyPageLevelStep(step = 2)
}