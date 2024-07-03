package team.ppac.search.search.mvi

import team.ppac.common.android.base.UiSideEffect

sealed class SearchSideEffect : UiSideEffect {
    data object NavigateToSearchDetail : SearchSideEffect()
}