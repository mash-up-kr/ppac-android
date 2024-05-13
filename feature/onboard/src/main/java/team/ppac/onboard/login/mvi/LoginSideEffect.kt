package team.ppac.onboard.login.mvi

import team.ppac.base.UiSideEffect

sealed class LoginSideEffect : UiSideEffect {
    data object Toast : LoginSideEffect()
    data object ScrollToBottom : LoginSideEffect()
}