package team.ppac.search.main.mvi

import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf
import team.ppac.common.android.base.UiState
import team.ppac.search.main.model.RecommendKeywordUiModel
import team.ppac.search.main.model.HotKeywordUiModel

data class SearchUiState(
    val isLoading: Boolean,
    val showServiceOpenDialog: Boolean,
    val hotKeywords: ImmutableList<HotKeywordUiModel>,
    val memeCategories: ImmutableList<RecommendKeywordUiModel>,
) : UiState {

    companion object {
        val INITIAL_STATE = SearchUiState(
            isLoading = false,
            showServiceOpenDialog = false,
            hotKeywords = persistentListOf(
                HotKeywordUiModel(
                    description = "키워드가 길어지면 말줄임",
                    imageUrl = ""
                ),
                HotKeywordUiModel(
                    description = "출근",
                    imageUrl = ""
                ),
                HotKeywordUiModel(
                    description = "슬픈",
                    imageUrl = ""
                ),
                HotKeywordUiModel(
                    description = "직장인",
                    imageUrl = ""
                ),
            ),
            memeCategories = persistentListOf(),
        )
    }
}