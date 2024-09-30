package team.ppac.search.main.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import team.ppac.designsystem.util.extension.noRippleClickable

/**
 * 임시 컴포넌트입니다.
 * 추후에 InputField 들어가면 제거 필요합니다.
 * @author JaesungLeee
 */
@Composable
internal fun FarmemeSearchBar(
    modifier: Modifier = Modifier,
    onSearchClick: () -> Unit
) {
    Box(modifier = Modifier
        .background(FarmemeTheme.backgroundColor.white)
        .padding(horizontal = 20.dp, vertical = 16.dp)
    ) {
        Row(
            modifier = modifier
                .fillMaxWidth()
                .noRippleClickable(onClick = onSearchClick)
                .clip(FarmemeRadius.Radius10.shape)
                .background(color = FarmemeTheme.backgroundColor.assistive)
                .padding(horizontal = 16.dp, vertical = 12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            FarmemeIcon.Search(
                modifier = Modifier.size(20.dp),
                tint = FarmemeTheme.iconColor.secondary
            )
            Spacer(modifier = Modifier.size(12.dp))
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = "찾고 싶은 밈 있어?",
                style = FarmemeTheme.typography.body.large.medium.copy(
                    color = FarmemeTheme.textColor.tertiary
                )
            )
        }
    }
}

@Preview
@Composable
private fun SearchBarPreview() {
    FarmemeSearchBar(
        onSearchClick = {}
    )
}