package team.ppac.analytics.action

import team.ppac.analytics.type.ActionType

enum class SettingsAction(private val tag: String) : ActionType {
    CLICK_APP_UPDATE("click_app_update"),
    ;

    override fun getAction(): String = tag
}