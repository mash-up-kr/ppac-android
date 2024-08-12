package team.ppac.search.detail.mvi

import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import team.ppac.common.android.base.UiState
import team.ppac.search.detail.model.SearchResultUiModel

data class SearchDetailUiState(
    val isLoading: Boolean,
    val isError: Boolean,
    val totalMemeCount: Int,
    val memeCategory: String,
    val searchResults: Flow<PagingData<SearchResultUiModel>>,
) : UiState {

    companion object {
        val INITIAL_STATE = SearchDetailUiState(
            isLoading = true,
            isError = false,
            totalMemeCount = 0,
            memeCategory = "",
            searchResults = flowOf(PagingData.empty())
        )
    }
}
