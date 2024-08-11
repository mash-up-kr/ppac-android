package team.ppac.search.detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import androidx.paging.LoadState
import androidx.paging.LoadStates
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.map
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import team.ppac.common.android.base.BaseViewModel
import team.ppac.domain.model.MemeWatchType
import team.ppac.domain.usecase.GetSearchMemeUseCase
import team.ppac.domain.usecase.WatchMemeUseCase
import team.ppac.errorhandling.FarmemeNetworkException
import team.ppac.search.detail.model.SearchResultUiModel
import team.ppac.search.detail.model.toSearchResultUiModel
import team.ppac.search.detail.mvi.SearchDetailIntent
import team.ppac.search.detail.mvi.SearchDetailSideEffect
import team.ppac.search.detail.mvi.SearchDetailUiState
import javax.inject.Inject

@HiltViewModel
class SearchDetailViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val getSearchMemeUseCase: GetSearchMemeUseCase,
    private val watchMemeUseCase: WatchMemeUseCase,
) : BaseViewModel<SearchDetailUiState, SearchDetailSideEffect, SearchDetailIntent>(savedStateHandle) {

    init {
        val memeCategory = savedStateHandle.get<String>("memeCategory") ?: ""
        getSearchResults(memeCategory)
    }

    override fun createInitialState(savedStateHandle: SavedStateHandle): SearchDetailUiState {
        return SearchDetailUiState.INITIAL_STATE
    }

    override fun handleClientException(throwable: Throwable) {}

    override suspend fun handleIntent(intent: SearchDetailIntent) {
        when (intent) {
            is SearchDetailIntent.ClickErrorRetry -> {
                getSearchResults(currentState.memeCategory)
                updateErrorState(isError = false)
            }

            is SearchDetailIntent.ClickMeme -> {
                runCatching {
                    watchMemeUseCase(
                        memeId = intent.memeId,
                        watchType = MemeWatchType.SEARCH
                    )
                }.onSuccess {
                    postSideEffect(SearchDetailSideEffect.NavigateToMemeDetail(memeId = intent.memeId))
                }.onFailure {
                    // 에러 처리
                }
            }
        }
    }

    fun handleLoadErrorStates(loadStates: LoadStates) {
        val errorLoadState = arrayOf(
            loadStates.prepend,
            loadStates.append,
            loadStates.refresh
        ).filterIsInstance(LoadState.Error::class.java).firstOrNull()

        val exception = errorLoadState?.error
        if (exception != null) {
            when (exception) {
                is FarmemeNetworkException -> {
                    updateErrorState(isError = true)
                }
            }
        }
    }

    private fun getSearchResults(memeCategory: String) = launch {
        updateLoadingState(true)
        delay(300L)

        val paginationResults = getSearchMemeUseCase(memeCategory)
        val totalMemeCount = paginationResults.totalMemeCount
        val searchResults = paginationResults.memes
            .map { pagingData ->
                pagingData.map { it.toSearchResultUiModel() }
            }.cachedIn(viewModelScope)

        updateLoadingState(false)
        updateSearchResults(totalMemeCount, memeCategory, searchResults)
    }

    private fun updateSearchResults(
        totalMemeCount: Int,
        memeCategory: String,
        searchResults: Flow<PagingData<SearchResultUiModel>>,
    ) {
        reduce {
            copy(
                totalMemeCount = totalMemeCount,
                memeCategory = memeCategory,
                searchResults = searchResults
            )
        }
    }

    private fun updateLoadingState(isLoading: Boolean) {
        reduce { copy(isLoading = isLoading) }
    }

    private fun updateErrorState(isError: Boolean) {
        reduce { copy(isError = isError) }
    }
}