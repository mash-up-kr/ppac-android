package team.ppac.search.detail.mvi

import team.ppac.common.android.base.UiState

data class SearchDetailUiState(
    val memeCategory: String,
) : UiState {

    companion object {
        val INITIAL_STATE = SearchDetailUiState(
            memeCategory = ""
        )
    }
}
