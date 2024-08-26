package team.ppac.analytics.action

import team.ppac.analytics.type.ActionType

enum class MemeDetailAction(private val tag: String) : ActionType {
    VIEW_MEME("view_meme"),
    CLICK_REACTION("click_reaction"),
    CLICK_COPY("click_copy"),
    CLICK_SHARE("click_share"),
    CLICK_SAVE("click_save"),
    CLICK_SAVE_CANCEL("click_save_cancel"),
    CLICK_TAG("click_tag"),
    ;

    override fun getAction(): String = tag
}