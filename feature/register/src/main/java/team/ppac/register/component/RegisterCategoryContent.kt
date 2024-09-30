package team.ppac.register.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf
import team.ppac.designsystem.FarmemeTheme
import team.ppac.designsystem.component.chip.FarmemeMediumChip
import team.ppac.register.model.RegisterCategoryUiModel

@Composable
internal fun RegisterCategoryContent(
    modifier: Modifier = Modifier,
    uiModel: RegisterCategoryUiModel,
) {
    Column(
        modifier = modifier,
    ) {
        RegisterCategoryHeader(title = uiModel.category)
        RegisterCategoryChips(
            keywords = uiModel.keywords,
            onKeywordClick = {},
        )
        Spacer(modifier = Modifier.height(24.dp))
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
internal fun RegisterCategoryChips(
    modifier: Modifier = Modifier,
    keywords: ImmutableList<String>,
    onKeywordClick: (String) -> Unit,
) {
    FlowRow(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp, Alignment.Start),
        verticalArrangement = Arrangement.spacedBy(8.dp, Alignment.Top),
    ) {
        repeat(keywords.size) { index ->
            val keyword = keywords[index]
            FarmemeMediumChip(
                text = keyword,
                onClick = { onKeywordClick(keyword) }
            )
        }
    }
}

@Preview
@Composable
private fun RegisterCategoryContentPreview() {
    RegisterCategoryContent(
        uiModel = RegisterCategoryUiModel.INITIAL_STATE,
    )
}

@Preview
@Composable
private fun RegisterCategoryChipsPreview() {
    Box(modifier = Modifier.background(FarmemeTheme.backgroundColor.white)) {
        RegisterCategoryChips(
            keywords = persistentListOf(
                "행복",
                "슬픈",
                "분노",
                "웃긴",
                "피곤",
                "절망",
                "현타",
                "당황",
                "무념무상",
            ),
            onKeywordClick = {}
        )
    }
}