package team.ppac.setting.mvi

import android.content.Context
import team.ppac.common.android.base.UiIntent

sealed class SettingIntent : UiIntent {
    data class InitView(val context: Context) : SettingIntent()
    data object ClickBackButton : SettingIntent()
    data object ClickPrivacyPolicy : SettingIntent()
    data class ClickAppUpdateButton(val context: Context) : SettingIntent()
}