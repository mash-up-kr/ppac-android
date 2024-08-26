package team.ppac.analytics.action

import team.ppac.analytics.type.ActionType

enum class SearchDetailAction(private val tag: String) : ActionType {
    SCROLL("scroll"),
    CLICK_COPY("click_copy"),
    CLICK_MEME("click_meme"),
    ;

    override fun getAction(): String = tag
}