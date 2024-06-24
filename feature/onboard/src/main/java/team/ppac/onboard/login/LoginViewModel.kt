package team.ppac.onboard.login

import dagger.hilt.android.lifecycle.HiltViewModel
import team.ppac.common.android.base.BaseViewModel
import team.ppac.onboard.login.mvi.LoginIntent
import team.ppac.onboard.login.mvi.LoginSideEffect
import team.ppac.onboard.login.mvi.LoginState
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor() :
    BaseViewModel<LoginState, LoginSideEffect, LoginIntent>(LoginState()) {
    override suspend fun handleIntent(intent: LoginIntent) {
        when (intent) {
            is LoginIntent.ClickLoginButton -> {
                postSideEffect(LoginSideEffect.NavigateToSample)
            }
        }
    }
}