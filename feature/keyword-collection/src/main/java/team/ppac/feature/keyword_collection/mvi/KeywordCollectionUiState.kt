package team.ppac.feature.keyword_collection.mvi

import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import team.ppac.common.android.base.UiState
import team.ppac.domain.model.Meme

data class KeywordCollectionUiState(
    val isLoading: Boolean,
    val isError: Boolean,
    val totalMemeCount: Int,
    val keyword: String,
    val memes: Flow<PagingData<Meme>>,
) : UiState {

    companion object {
        val INITIAL_STATE = KeywordCollectionUiState(
            isLoading = true,
            isError = false,
            totalMemeCount = 0,
            keyword = "",
            memes = flowOf(PagingData.empty())
        )
    }
}
