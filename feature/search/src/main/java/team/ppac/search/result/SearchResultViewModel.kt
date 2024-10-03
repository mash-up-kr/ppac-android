package team.ppac.search.result

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
import team.ppac.domain.usecase.SearchMemeUseCase
import team.ppac.domain.usecase.WatchMemeUseCase
import team.ppac.errorhandling.FarmemeNetworkException
import team.ppac.search.detail.model.SearchResultUiModel
import team.ppac.search.detail.model.toSearchResultUiModel
import team.ppac.search.detail.mvi.SearchDetailSideEffect
import team.ppac.search.result.mvi.ClickBackButton
import team.ppac.search.result.mvi.ClickErrorRetry
import team.ppac.search.result.mvi.ClickMeme
import team.ppac.search.result.mvi.NavigateBack
import team.ppac.search.result.mvi.NavigateToMemeDetail
import team.ppac.search.result.mvi.SearchResultIntent
import team.ppac.search.result.mvi.SearchResultSideEffect
import team.ppac.search.result.mvi.SearchResultUiState
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class SearchResultViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val searchMemeUseCase: SearchMemeUseCase,
    private val watchMemeUseCase: WatchMemeUseCase,
) : BaseViewModel<SearchResultUiState, SearchResultSideEffect, SearchResultIntent>(savedStateHandle) {

    init {
        val query = savedStateHandle.get<String>("query").orEmpty()
        reduce { copy(query = query) }
        getSearchResults(query)
    }

    override fun createInitialState(savedStateHandle: SavedStateHandle): SearchResultUiState {
        return SearchResultUiState.INITIAL_STATE
    }

    override suspend fun handleIntent(intent: SearchResultIntent) {
        when (intent) {
            is ClickBackButton -> postNavigateBackEffect()
            is ClickErrorRetry -> {
                getSearchResults(currentState.query)
                updateErrorState(isError = false)
            }
            is ClickMeme -> {
                runCatching {
                    watchMemeUseCase(
                        memeId = intent.memeId,
                        watchType = MemeWatchType.SEARCH
                    )
                }.onSuccess {
                    postNavigateToMemeDetailEffect(intent.memeId)
                }.onFailure {
                    // 에러 처리
                }
            }
        }
    }

    private fun postNavigateToMemeDetailEffect(memeId: String) {
        postSideEffect(NavigateToMemeDetail(memeId = memeId))
    }

    override fun handleClientException(throwable: Throwable) {}

    private fun postNavigateBackEffect() {
        postSideEffect(NavigateBack)
    }

    fun getSearchResults(query: String) = launch {
        runCatching {
            updateLoadingState(true)
            delay(300L)
            val paginationResults = searchMemeUseCase(query)
            val totalMemeCount = paginationResults.totalMemeCount
            val searchResults = paginationResults.memes
                .map { pagingData ->
                    pagingData.map {
                        it.toSearchResultUiModel() }
                }.cachedIn(viewModelScope)

            updateLoadingState(false)
            updateSearchResults(totalMemeCount, searchResults)
        }.onFailure {
            when (it) {
                is FarmemeNetworkException -> {
                    reduce {
                        copy(
                            isError = true,
                            isLoading = false,
                        )
                    }
                }
            }
        }
    }

    fun updateSearchQuery(query: String) {
        reduce { copy(query = query) }
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

    private fun updateSearchResults(
        totalMemeCount: Int,
        searchResults: Flow<PagingData<SearchResultUiModel>>,
    ) {
        reduce {
            copy(
                totalMemeCount = totalMemeCount,
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