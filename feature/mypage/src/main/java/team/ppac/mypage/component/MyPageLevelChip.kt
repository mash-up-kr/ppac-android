package team.ppac.mypage.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import team.ppac.designsystem.FarmemeTheme
import team.ppac.designsystem.foundation.FarmemeIcon
import team.ppac.designsystem.foundation.FarmemeRadius

@Composable
internal fun MyPageLevelChip(
    modifier: Modifier = Modifier,
    memeCount: Int,
) {
    Row(
        modifier = modifier
            .clip(FarmemeRadius.Radius30.shape)
            .background(FarmemeTheme.backgroundColor.white)
            .padding(horizontal = 10.dp, vertical = 5.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(
            text = "$memeCount",
            color = FarmemeTheme.textColor.brand,
            style = FarmemeTheme.typography.body.large.semibold,
        )
        Text(
            text = "/$MAX_MEME_COUNT",
            color = FarmemeTheme.textColor.tertiary,
            style = FarmemeTheme.typography.body.large.semibold,
        )
    }
}

@Composable
internal fun MyPageLevelCompletedChip(
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier
            .clip(FarmemeRadius.Radius30.shape)
            .background(FarmemeTheme.backgroundColor.white)
            .padding(horizontal = 10.dp, vertical = 5.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(
            text = "달성 완료",
            color = FarmemeTheme.textColor.primary,
            style = FarmemeTheme.typography.body.large.semibold,
        )
        Spacer(modifier = Modifier.width(4.dp))
        FarmemeIcon.LevelCheck()
    }
}

@Preview
@Composable
private fun MyPageLevelChipPreview() {
    MyPageLevelChip(memeCount = 10)
}

@Preview
@Composable
private fun MyPageLevelCompletedChipPreview() {
    MyPageLevelCompletedChip()
}