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
import team.ppac.mypage.model.LevelUiModel
import team.ppac.mypage.model.MyPageLevel

@Composable
internal fun MyPageLevelStep(
    modifier: Modifier = Modifier,
    levelUiModel: LevelUiModel,
) {
    Column(
        modifier = modifier.fillMaxWidth(),
    ) {
        MyPageLevelStepProgress(levelUiModel = levelUiModel)
        Spacer(modifier = Modifier.height(10.dp))
        MyPageStepChips(levelUiModel = levelUiModel)
    }
}

@Composable
private fun MyPageLevelStepProgress(
    modifier: Modifier = Modifier,
    levelUiModel: LevelUiModel,
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 20.5.dp),
        contentAlignment = Alignment.Center,
    ) {
        MyPageDottedLines(level = levelUiModel.level.level)
        MyPageStepIcons(levelUiModel = levelUiModel)
    }
}

@Composable
private fun MyPageDottedLines(
    modifier: Modifier = Modifier,
    level: Int,
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
            enabled = level > MyPageLevel.LEVEL1.level,
        )
        MyPageDottedLine(
            modifier = Modifier
                .weight(1.0f),
            enabled = level > MyPageLevel.LEVEL2.level,
        )
        MyPageDottedLine(
            modifier = Modifier
                .weight(1.0f)
                .padding(end = 12.dp),
            enabled = level > MyPageLevel.LEVEL3.level,
        )
    }
}

@Composable
private fun MyPageDottedLine(
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
private fun MyPageStepIcons(
    modifier: Modifier = Modifier,
    levelUiModel: LevelUiModel,
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        for (idx in MyPageLevel.LEVEL1.level..MyPageLevel.entries.size) {
            when {
                (idx < levelUiModel.level.level)
                        || (levelUiModel.level.level == MyPageLevel.LEVEL4.level && levelUiModel.memeCount == MAX_MEME_COUNT)
                -> FarmemeIcon.LevelCheck()

                idx == levelUiModel.level.level
                -> FarmemeIcon.LevelCurrent()

                else
                -> FarmemeIcon.LevelDisabled()
            }
        }
    }
}

@Composable
private fun MyPageStepChips(
    modifier: Modifier = Modifier,
    levelUiModel: LevelUiModel,
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        FarmemeSmallChip(
            text = "밈 보기",
            enabled = (levelUiModel.level.level >= MyPageLevel.LEVEL2.level),
        )
        FarmemeSmallChip(
            text = "ㅋ 남기기",
            enabled = (levelUiModel.level.level >= MyPageLevel.LEVEL3.level),
        )
        FarmemeSmallChip(
            text = "밈 공유",
            enabled = (levelUiModel.level.level >= MyPageLevel.LEVEL4.level),
        )
        FarmemeSmallChip(
            text = "밈 저장",
            enabled = (levelUiModel.level.level >= MyPageLevel.LEVEL4.level)
                    && (levelUiModel.memeCount == MAX_MEME_COUNT),
        )
    }
}

@Preview
@Composable
private fun MyPageLevelStepPreview() {
    MyPageLevelStep(
        levelUiModel = LevelUiModel(
            level = MyPageLevel.LEVEL3,
            memeCount = 15,
        )
    )
}

private const val MAX_MEME_COUNT = 20