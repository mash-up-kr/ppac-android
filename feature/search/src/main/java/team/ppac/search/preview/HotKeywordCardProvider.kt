package team.ppac.search.preview

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import team.ppac.search.model.HotKeywordUiModel

class HotKeywordCardProvider : PreviewParameterProvider<HotKeywordUiModel> {
    override val values: Sequence<HotKeywordUiModel>
        get() = sequenceOf(
            HotKeywordUiModel(
                description = "키워드가 길어지면 말줄임",
                imageUrl = null
            ),
            HotKeywordUiModel(
                description = "출근",
                imageUrl = null
            ),
            HotKeywordUiModel(
                description = "슬픈",
                imageUrl = null
            ),
            HotKeywordUiModel(
                description = "직장인",
                imageUrl = null
            ),
        )
}