package team.ppac.analytics.action

import team.ppac.analytics.type.ActionType

enum class SearchAction(private val tag: String) : ActionType {
    CLICK_SEARCH_BAR("click_search_bar"),
    CLICK_HOT_KEYWORD("click_hot_keyword"),
    CLICK_KEYWORD("click_keyword"),
    ;

    override fun getAction(): String = tag
}