package team.ppac.splash

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.job
import kotlinx.coroutines.launch
import team.ppac.common.android.base.BaseViewModel
import team.ppac.domain.usecase.UserCreateUseCase
import team.ppac.splash.mvi.SplashIntent
import team.ppac.splash.mvi.SplashSideEffect
import team.ppac.splash.mvi.SplashState
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val userCreateUseCase: UserCreateUseCase,
) : BaseViewModel<SplashState, SplashSideEffect, SplashIntent>(savedStateHandle) {

    init {
        viewModelScope.launch {
            launch {
                userCreateUseCase()
            }
            launch {
                delay(500L) //splash delay
            }
            coroutineContext.job.children.forEach { job ->
                job.join()
            }
            postSideEffect(SplashSideEffect.NavigateToMain)
        }
    }

    override fun createInitialState(savedStateHandle: SavedStateHandle): SplashState {
        return SplashState
    }

    override suspend fun handleIntent(intent: SplashIntent) {}
}