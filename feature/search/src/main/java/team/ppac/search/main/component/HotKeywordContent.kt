package team.ppac.search.main.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf
import team.ppac.designsystem.FarmemeTheme
import team.ppac.search.main.model.HotKeywordUiModel

@Composable
internal fun HotKeywordContent(
    modifier: Modifier = Modifier,
    keywords: ImmutableList<HotKeywordUiModel>
) {
    LazyRow(
        modifier = modifier.fillMaxWidth(),
        contentPadding = PaddingValues(horizontal = 20.dp),
        horizontalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        items(
            items = keywords,
            key = { it.id },
        ) { uiModel ->
            HotKeywordCard(
                hotKeywordUiModel = uiModel,
            )
        }
    }
}

@Preview
@Composable
private fun HotKeywordContentPreview() {
    val sampleUrl = "https://picsum.photos/id/10/2500/1667"

    Box(modifier = Modifier.background(FarmemeTheme.backgroundColor.white)) {
        HotKeywordContent(
            keywords = persistentListOf<HotKeywordUiModel>().add(
                HotKeywordUiModel(id = "", keyword = "Asdf", imageUrl = sampleUrl)
            ).add(
                HotKeywordUiModel(id = "", keyword = "Asdf", imageUrl = sampleUrl)
            ).add(
                HotKeywordUiModel(id = "", keyword = "Asdf", imageUrl = sampleUrl)
            ).add(
                HotKeywordUiModel(id = "", keyword = "Asdf", imageUrl = sampleUrl)
            ).add(
                HotKeywordUiModel(id = "", keyword = "Asdf", imageUrl = sampleUrl)
            )
        )
    }
}