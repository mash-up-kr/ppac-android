package team.ppac.search.main.mvi

import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf
import team.ppac.common.android.base.UiState
import team.ppac.search.main.model.HotKeywordUiModel
import team.ppac.search.main.model.RecommendKeywordUiModel

data class SearchUiState(
    val isLoading: Boolean,
    val isError: Boolean,
    val showServiceOpenDialog: Boolean,
    val hotKeywords: ImmutableList<HotKeywordUiModel>,
    val memeCategories: ImmutableList<RecommendKeywordUiModel>,
) : UiState {

    companion object {
        val INITIAL_STATE = SearchUiState(
            isLoading = true,
            isError = false,
            showServiceOpenDialog = false,
            hotKeywords = persistentListOf(),
            memeCategories = persistentListOf(),
        )
    }
}