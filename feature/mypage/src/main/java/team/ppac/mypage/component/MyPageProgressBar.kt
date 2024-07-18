package team.ppac.mypage.component

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import team.ppac.designsystem.FarmemeTheme
import team.ppac.designsystem.foundation.FarmemeIcon
import team.ppac.designsystem.foundation.FarmemeRadius
import team.ppac.mypage.model.LevelUiModel
import team.ppac.mypage.model.MyPageLevel

@Composable
internal fun MyPageProgressBar(
    modifier: Modifier = Modifier,
    levelUiModel: LevelUiModel,
) {
    BoxWithConstraints(
        modifier = modifier,
    ) {
        MyPageProgressBarBackground()
        MyPageProgressBarActive(
            maxWidth = maxWidth,
            levelUiModel = levelUiModel,
        )
    }
}

@Composable
private fun MyPageProgressBarActive(
    modifier: Modifier = Modifier,
    maxWidth: Dp,
    levelUiModel: LevelUiModel,
) {
    val minWidth = 96f.dp
    val currentWidth = minWidth + (maxWidth - minWidth) * levelUiModel.memeCount * 0.05f

    var progress by remember { mutableStateOf(minWidth) }
    val progressAnimation by animateDpAsState(
        targetValue = progress,
        animationSpec = tween(
            durationMillis = 1_500,
            easing = FastOutSlowInEasing
        ),
    )

    LaunchedEffect(LocalLifecycleOwner.current) {
        progress = currentWidth
    }

    Row(
        modifier = modifier
            .width(progressAnimation)
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
        when (levelUiModel.level) {
            MyPageLevel.LEVEL1 -> FarmemeIcon.Level1(modifier = Modifier.size(24.dp))
            MyPageLevel.LEVEL2 -> FarmemeIcon.Level2(modifier = Modifier.size(24.dp))
            MyPageLevel.LEVEL3 -> FarmemeIcon.Level3(modifier = Modifier.size(24.dp))
            MyPageLevel.LEVEL4 -> FarmemeIcon.Level4(modifier = Modifier.size(24.dp))
        }
        Spacer(Modifier.width(4.dp))
        Text(
            text = "LV. ${levelUiModel.level.level}",
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
        levelUiModel = LevelUiModel(
            level = MyPageLevel.LEVEL3,
            memeCount = 15,
        )
    )
}