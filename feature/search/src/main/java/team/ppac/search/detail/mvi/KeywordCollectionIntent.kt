package team.ppac.search.detail.mvi

import team.ppac.common.android.base.UiIntent

sealed class KeywordCollectionIntent : UiIntent {
    data object ClickErrorRetry : KeywordCollectionIntent()
    data class ClickMeme(
        val memeId: String,
    ) : KeywordCollectionIntent()
}