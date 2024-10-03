package team.ppac.search.result.mvi

import team.ppac.common.android.base.UiSideEffect

sealed interface SearchResultSideEffect : UiSideEffect
data object NavigateBack : SearchResultSideEffect
data class NavigateToMemeDetail(val memeId: String) : SearchResultSideEffect
