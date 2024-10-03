package team.ppac.search.main.mvi

import team.ppac.common.android.base.UiIntent

sealed class SearchIntent : UiIntent {
    data object ClickErrorRetry : SearchIntent()
    data class InputDone(val query: String) : SearchIntent()
    data class ClickKeywordCard(val keyword: String) : SearchIntent()
    data class ClickMemeKeyword(val keyword: String) : SearchIntent()
    data object ClickServiceOpenDialogConfirm : SearchIntent()
    data object ClickServiceOpenDialogDismiss : SearchIntent()
}