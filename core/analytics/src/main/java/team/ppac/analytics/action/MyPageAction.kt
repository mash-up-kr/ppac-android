package team.ppac.analytics.action

import team.ppac.analytics.type.ActionType

enum class MyPageAction(private val tag: String) : ActionType {
    SCROLL("scroll"),
    CLICK_MEME("click_meme"),
    CLICK_COPY("click_copy"),
    CLICK_SETTINGS("click_settings"),
    CLICK_REGISTER("click_register"),
    ;

    override fun getAction(): String = tag
}