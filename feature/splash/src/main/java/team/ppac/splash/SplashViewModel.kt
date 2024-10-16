package team.ppac.splash

import androidx.lifecycle.SavedStateHandle
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.job
import kotlinx.coroutines.launch
import team.ppac.common.android.base.BaseViewModel
import team.ppac.domain.usecase.CreateUserUseCase
import team.ppac.errorhandling.FarmemeNetworkException
import team.ppac.splash.mvi.SplashIntent
import team.ppac.splash.mvi.SplashSideEffect
import team.ppac.splash.mvi.SplashState
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val userCreateUseCase: CreateUserUseCase,
) : BaseViewModel<SplashState, SplashSideEffect, SplashIntent>(savedStateHandle) {

    init {
        launch {
            launch {
                userCreateUseCase()
            }
            launch {
                delay(700L) //splash delay
            }
            coroutineContext.job.children.forEach { job ->
                job.join()
            }
            postSideEffect(SplashSideEffect.NavigateToMain)
        }
    }

    override fun createInitialState(savedStateHandle: SavedStateHandle): SplashState {
        return SplashState()
    }

    override fun handleClientException(throwable: Throwable) {
        if (throwable is FarmemeNetworkException) {
            reduce {
                copy(isNetworkError = true)
            }
        }
    }

    override suspend fun handleIntent(intent: SplashIntent) {
        when (intent) {
            SplashIntent.ClickDialogConfirm -> {
                reduce { copy(isNetworkError = false) }
                postSideEffect(SplashSideEffect.ForceFinish)
            }
        }
    }
}