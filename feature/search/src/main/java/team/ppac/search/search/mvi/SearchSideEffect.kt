package team.ppac.search.search.mvi

import team.ppac.common.android.base.UiSideEffect

sealed class SearchSideEffect : UiSideEffect {
    data class NavigateToSearchDetail(
        val category: String,
    ) : SearchSideEffect()
}