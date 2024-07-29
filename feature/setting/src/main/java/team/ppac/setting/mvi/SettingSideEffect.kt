package team.ppac.setting.mvi

import team.ppac.common.android.base.UiSideEffect

sealed class SettingSideEffect : UiSideEffect {
    data object OnClickBackButton : SettingSideEffect()
    data object NavigateToPrivacyPolicy : SettingSideEffect()
}