package team.ppac.search.detail.mvi

import team.ppac.common.android.base.UiIntent

sealed class SearchDetailIntent : UiIntent {
    data object ClickErrorRetry : SearchDetailIntent()
    data class ClickMeme(
        val memeId: String,
    ) : SearchDetailIntent()
}