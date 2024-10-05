package team.ppac.search.detail.mvi

import team.ppac.common.android.base.UiSideEffect

sealed class KeywordCollectionSideEffect : UiSideEffect {
    data class NavigateToMemeDetail(
        val memeId: String,
    ) : KeywordCollectionSideEffect()
}