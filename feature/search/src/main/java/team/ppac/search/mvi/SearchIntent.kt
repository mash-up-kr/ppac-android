package team.ppac.search.mvi

import team.ppac.common.android.base.UiIntent

sealed class SearchIntent : UiIntent {
    data object ClickSearch : SearchIntent()
    data object ClickKeywordCard : SearchIntent()
    data object ClickMemeCategory : SearchIntent()
}