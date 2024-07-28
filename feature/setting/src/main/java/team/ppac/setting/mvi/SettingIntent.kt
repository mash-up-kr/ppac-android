package team.ppac.setting.mvi

import team.ppac.common.android.base.UiIntent

sealed class SettingIntent : UiIntent {
    data object ClickBackButton : SettingIntent()
    data object ClickSettingListItem : SettingIntent()
}