package team.ppac.search.result.mvi

import team.ppac.common.android.base.UiState

data class SearchResultUiState(
    val isLoading: Boolean,
    val query: String
) : UiState {

    companion object {
        val INITIAL_STATE = SearchResultUiState(
            isLoading = true,
            query = ""
        )
    }
}