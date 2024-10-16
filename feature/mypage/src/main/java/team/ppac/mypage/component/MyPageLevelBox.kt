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
import team.ppac.common.android.util.visibility
import team.ppac.designsystem.FarmemeTheme
import team.ppac.designsystem.foundation.FarmemeRadius
import team.ppac.mypage.model.LevelUiModel
import team.ppac.mypage.model.MyPageLevel

@Composable
internal fun MyPageLevelBox(
    modifier: Modifier = Modifier,
    levelUiModel: LevelUiModel,
    isLoading: Boolean,
) {
    Column(
        modifier = modifier
            .clip(FarmemeRadius.Radius20.shape)
            .border(
                width = 1.dp,
                color = FarmemeTheme.borderColor.tertiary,
                shape = FarmemeRadius.Radius20.shape,
            )
    ) {
        MyPageLevelTop(
            levelUiModel = levelUiModel,
            isLoading = isLoading,
        )
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(1.dp)
                .background(FarmemeTheme.borderColor.tertiary)
        )
        MyPageLevelBottom(
            modifier = Modifier.visibility(isVisible = !isLoading),
            levelUiModel = levelUiModel,
        )
    }
}

@Composable
private fun MyPageLevelTop(
    modifier: Modifier = Modifier,
    levelUiModel: LevelUiModel,
    isLoading: Boolean,
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
        MyPageLevelTitle(
            modifier = Modifier.visibility(isVisible = !isLoading),
            levelUiModel = levelUiModel,
        )

        if (levelUiModel.isMaxLevel()) {
            MyPageLevelCompletedChip(
                modifier = Modifier.visibility(isVisible = !isLoading),
            )
        } else {
            MyPageLevelChip(
                modifier = Modifier.visibility(isVisible = !isLoading),
                memeCount = levelUiModel.memeCount,
            )
        }
    }
}

@Composable
private fun MyPageLevelTitle(
    modifier: Modifier = Modifier,
    levelUiModel: LevelUiModel,
) {
    Column(
        modifier = modifier,
    ) {
        Spacer(modifier = Modifier.height(6.dp))
        Text(
            text = when (levelUiModel.myPageLevel) {
                MyPageLevel.LEVEL4 -> "최종 레벨 달성 미션"
                else -> "레벨업하고 싶다면"
            },
            color = FarmemeTheme.textColor.tertiary,
            style = FarmemeTheme.typography.body.medium.semibold,
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = levelUiModel.myPageLevel.stepTitle,
            color = FarmemeTheme.textColor.primary,
            style = FarmemeTheme.typography.heading.small.semibold,
        )
    }
}

@Composable
private fun MyPageLevelBottom(
    modifier: Modifier = Modifier,
    levelUiModel: LevelUiModel,
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .background(FarmemeTheme.backgroundColor.white)
            .padding(start = 20.dp, top = 20.dp, end = 20.dp, bottom = 30.dp),
    ) {
        MyPageLevelStep(levelUiModel = levelUiModel)
    }
}

@Preview
@Composable
private fun MyPageLevelPreview() {
    MyPageLevelBox(
        levelUiModel = LevelUiModel(
            myPageLevel = MyPageLevel.LEVEL3,
            memeCount = 15,
        ),
        isLoading = false,
    )
}