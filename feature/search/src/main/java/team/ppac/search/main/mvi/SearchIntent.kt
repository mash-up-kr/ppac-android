package team.ppac.search.main.mvi

import team.ppac.common.android.base.UiIntent

sealed class SearchIntent : UiIntent {
    data object ClickErrorRetry : SearchIntent()
    data object ClickSearch : SearchIntent()
    data class ClickKeywordCard(val keyword: String) : SearchIntent()
    data class ClickMemeCategory(val category: String) : SearchIntent()
    data object ClickServiceOpenDialogConfirm : SearchIntent()
    data object ClickServiceOpenDialogDismiss : SearchIntent()
}