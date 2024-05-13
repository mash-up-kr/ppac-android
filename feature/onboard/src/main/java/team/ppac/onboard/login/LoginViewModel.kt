package team.ppac.onboard.login

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import team.ppac.base.BaseViewModel
import team.ppac.onboard.login.mvi.LoginIntent
import team.ppac.onboard.login.mvi.LoginRepository
import team.ppac.onboard.login.mvi.LoginSideEffect
import team.ppac.onboard.login.mvi.LoginState
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginRepository: LoginRepository,
) : BaseViewModel<LoginState, LoginSideEffect, LoginIntent>(LoginState()) {

    private fun clickLoginButton() {
        viewModelScope.launch {
            val names = loginRepository.getNames()
            if(names.isEmpty()){
                reduce {
                    copy(nickname = "")
                }
            }else{
                // LazyColumn의 스크롤 포지션을 끝가지 이동시키겠다.?
                postSideEffect(LoginSideEffect.ScrollToBottom)
            }
        }
    }

    override suspend fun handleIntent(intent: LoginIntent) {
        when(intent){
            LoginIntent.ClickLoginButton -> clickLoginButton()
        }
    }

    fun setToastVisible(visible: Boolean){
        reduce {
            copy(toastVisible = visible)
        }
    }
}