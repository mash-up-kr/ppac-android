package team.ppac.onboard.login.mvi

import team.ppac.common.android.base.UiSideEffect

sealed class LoginSideEffect : UiSideEffect {
    data object NavigateToSample : LoginSideEffect()
}