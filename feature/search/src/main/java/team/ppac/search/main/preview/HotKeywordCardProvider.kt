package team.ppac.search.main.preview

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import team.ppac.search.main.model.HotKeywordUiModel

class HotKeywordCardProvider : PreviewParameterProvider<HotKeywordUiModel> {
    private val sampleUrl = "https://picsum.photos/id/10/2500/1667"

    override val values: Sequence<HotKeywordUiModel>
        get() = sequenceOf(
            HotKeywordUiModel(
                keywordId = "",
                keyword = "키워드가 길어지면 말줄임",
                imageUrl = sampleUrl
            ),
            HotKeywordUiModel(
                keywordId = "",
                keyword = "출근",
                imageUrl = sampleUrl
            ),
            HotKeywordUiModel(
                keywordId = "",
                keyword = "슬픈",
                imageUrl = sampleUrl
            ),
            HotKeywordUiModel(
                keywordId = "",
                keyword = "직장인",
                imageUrl = sampleUrl
            ),
        )
}