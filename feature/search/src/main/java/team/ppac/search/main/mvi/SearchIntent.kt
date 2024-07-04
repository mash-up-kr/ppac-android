package team.ppac.search.main.mvi

import team.ppac.common.android.base.UiIntent

sealed class SearchIntent : UiIntent {
    data class ClickSearch(val showDialog: Boolean) : SearchIntent()
    data object ClickKeywordCard : SearchIntent()
    data class ClickMemeCategory(val category: String) : SearchIntent()
}