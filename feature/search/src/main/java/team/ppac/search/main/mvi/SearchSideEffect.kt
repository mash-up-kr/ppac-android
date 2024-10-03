package team.ppac.search.main.mvi

import team.ppac.common.android.base.UiSideEffect

sealed class SearchSideEffect : UiSideEffect {
    data class NavigateToSearchDetail(val argument: String) : SearchSideEffect()
    data class NavigateToSearchResult(val argument: String) : SearchSideEffect()
}