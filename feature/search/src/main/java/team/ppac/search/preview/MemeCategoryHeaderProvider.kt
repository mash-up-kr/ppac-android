package team.ppac.search.preview

import androidx.compose.ui.tooling.preview.PreviewParameterProvider

class MemeCategoryHeaderProvider : PreviewParameterProvider<String> {

    override val values: Sequence<String>
        get() = sequenceOf("감정", "상황", "콘텐츠")
}