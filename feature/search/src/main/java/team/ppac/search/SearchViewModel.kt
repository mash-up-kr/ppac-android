package team.ppac.search

import dagger.hilt.android.lifecycle.HiltViewModel
import team.ppac.common.android.base.BaseViewModel
import team.ppac.search.mvi.SearchIntent
import team.ppac.search.mvi.SearchSideEffect
import team.ppac.search.mvi.SearchUiState
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(

) : BaseViewModel<SearchUiState, SearchSideEffect, SearchIntent>(SearchUiState.INITIAL_STATE){

    override suspend fun handleIntent(intent: SearchIntent) {
        when (intent) {
            SearchIntent.ClickSearch -> {
                updateServiceOpenDialog()
            }
            SearchIntent.ClickKeywordCard, SearchIntent.ClickMemeCategory -> {
                postSideEffect(SearchSideEffect.NavigateToSearchDetail)
            }
        }
    }

    private fun updateServiceOpenDialog(showServiceOpenDialog: Boolean = true) {
        reduce {
            copy(showServiceOpenDialog = showServiceOpenDialog)
        }
    }
}