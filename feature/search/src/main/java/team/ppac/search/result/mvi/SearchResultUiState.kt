package team.ppac.search.result.mvi

import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import team.ppac.common.android.base.UiState
import team.ppac.domain.model.Meme

data class SearchResultUiState(
    val isLoading: Boolean,
    val isError: Boolean,
    val query: String,
    val totalMemeCount: Int,
    val searchResults: Flow<PagingData<Meme>>,
) : UiState {

    companion object {
        val INITIAL_STATE = SearchResultUiState(
            isLoading = true,
            isError = false,
            query = "",
            totalMemeCount = 0,
            searchResults = flowOf(PagingData.empty()),
        )
    }
}