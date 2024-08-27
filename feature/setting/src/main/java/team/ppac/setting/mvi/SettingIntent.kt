package team.ppac.setting.mvi

import team.ppac.common.android.base.UiIntent

sealed class SettingIntent : UiIntent {
    data object ClickBackButton : SettingIntent()
    data object ClickPrivacyPolicy : SettingIntent()
    data object ClickAppUpdateButton : SettingIntent()
    data class CheckNewAppVersionAvailable(val hasNewAppVersion: Boolean) : SettingIntent()
}