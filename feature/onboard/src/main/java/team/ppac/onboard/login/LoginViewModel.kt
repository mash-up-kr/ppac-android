package team.ppac.onboard.login

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import team.ppac.base.BaseViewModel
import team.ppac.onboard.login.mvi.LoginIntent
import team.ppac.onboard.login.mvi.LoginSideEffect
import team.ppac.onboard.login.mvi.LoginState
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(): BaseViewModel<LoginState, LoginSideEffect, LoginIntent>(LoginState()){
    override suspend fun handleIntent(intent: LoginIntent) {

    }
}