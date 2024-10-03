package team.ppac.search.result.mvi

import team.ppac.common.android.base.UiIntent

sealed interface SearchResultIntent : UiIntent
data object ClickBackButton : SearchResultIntent
data object ClickErrorRetry : SearchResultIntent
data class ClickMeme(val memeId: String) : SearchResultIntent