package team.ppac.search.main.mvi

import team.ppac.common.android.base.UiIntent

sealed class SearchIntent : UiIntent {
    data object ClickSearch : SearchIntent()
    data object ClickKeywordCard : SearchIntent()
    data class ClickMemeCategory(val category: String) : SearchIntent()
    data object ClickServiceOpenDialogConfirm : SearchIntent()
    data object ClickServiceOpenDialogDismiss : SearchIntent()
}