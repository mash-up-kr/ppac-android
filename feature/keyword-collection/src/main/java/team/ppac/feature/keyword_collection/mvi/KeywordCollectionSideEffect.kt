package team.ppac.feature.keyword_collection.mvi

import team.ppac.common.android.base.UiSideEffect

sealed class KeywordCollectionSideEffect : UiSideEffect {
    data class NavigateToMemeDetail(
        val memeId: String,
    ) : KeywordCollectionSideEffect()
}