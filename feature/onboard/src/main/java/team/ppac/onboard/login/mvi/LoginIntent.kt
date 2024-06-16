package team.ppac.onboard.login.mvi

import team.ppac.common.android.base.UiIntent

sealed class LoginIntent : UiIntent {
    data object ClickLoginButton : LoginIntent()
}