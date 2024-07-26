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
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieAnimatable
import com.airbnb.lottie.compose.rememberLottieComposition
import team.ppac.designsystem.FarmemeTheme
import team.ppac.designsystem.R
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
        MyPageDottedLines(level = levelUiModel.myPageLevel.levelCount)
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
            enabled = level > MyPageLevel.LEVEL1.levelCount,
        )
        MyPageDottedLine(
            modifier = Modifier
                .weight(1.0f),
            enabled = level > MyPageLevel.LEVEL2.levelCount,
        )
        MyPageDottedLine(
            modifier = Modifier
                .weight(1.0f)
                .padding(end = 12.dp),
            enabled = level > MyPageLevel.LEVEL3.levelCount,
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
    val lottieComposition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.my_page_level_circle_effect))
    val lottieAnimatable = rememberLottieAnimatable()

    LaunchedEffect(lottieComposition) {
        lottieAnimatable.animate(
            composition = lottieComposition,
            iterations = LottieConstants.IterateForever,
        )
    }

    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        (MyPageLevel.LEVEL1.levelCount..MyPageLevel.LEVEL4.levelCount).map { step ->
            when {
                step < levelUiModel.myPageLevel.levelCount || levelUiModel.isMaxLevel() -> {
                    FarmemeIcon.LevelCheck()
                }

                step == levelUiModel.myPageLevel.levelCount -> {
                    Box(contentAlignment = Alignment.Center) {
                        LottieAnimation(
                            modifier = Modifier.size(20.dp),
                            composition = lottieComposition,
                            progress = { lottieAnimatable.progress },
                        )
                        FarmemeIcon.LevelDisabled()
                    }
                }

                step > levelUiModel.myPageLevel.levelCount -> {
                    FarmemeIcon.LevelDisabled()
                }
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
            enabled = (levelUiModel.myPageLevel.levelCount >= MyPageLevel.LEVEL2.levelCount),
        )
        FarmemeSmallChip(
            text = "ㅋ 남기기",
            enabled = (levelUiModel.myPageLevel.levelCount >= MyPageLevel.LEVEL3.levelCount),
        )
        FarmemeSmallChip(
            text = "밈 공유",
            enabled = (levelUiModel.myPageLevel.levelCount >= MyPageLevel.LEVEL4.levelCount),
        )
        FarmemeSmallChip(
            text = "밈 저장",
            enabled = levelUiModel.isMaxLevel(),
        )
    }
}

@Preview
@Composable
private fun MyPageLevelStepPreview() {
    MyPageLevelStep(
        levelUiModel = LevelUiModel(
            myPageLevel = MyPageLevel.LEVEL3,
            memeCount = 15,
        )
    )
}

fun LevelUiModel.isMaxLevel() =
    (this.myPageLevel.levelCount == MyPageLevel.LEVEL4.levelCount && this.memeCount >= MAX_MEME_COUNT)

const val MAX_MEME_COUNT = 20