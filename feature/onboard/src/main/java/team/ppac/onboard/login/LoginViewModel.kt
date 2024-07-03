package team.ppac.onboard.login

import androidx.lifecycle.SavedStateHandle
import dagger.hilt.android.lifecycle.HiltViewModel
import team.ppac.common.android.base.BaseViewModel
import team.ppac.onboard.login.mvi.LoginIntent
import team.ppac.onboard.login.mvi.LoginSideEffect
import team.ppac.onboard.login.mvi.LoginState
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
) : BaseViewModel<LoginState, LoginSideEffect, LoginIntent>(savedStateHandle) {

    override fun createInitialState(savedStateHandle: SavedStateHandle): LoginState {
        return LoginState()
    }

    override suspend fun handleIntent(intent: LoginIntent) {
        when (intent) {
            is LoginIntent.ClickLoginButton -> {
                postSideEffect(LoginSideEffect.NavigateToSample)
            }
        }
    }
}