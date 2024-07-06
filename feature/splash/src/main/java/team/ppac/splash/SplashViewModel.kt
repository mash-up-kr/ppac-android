package team.ppac.splash

import androidx.lifecycle.SavedStateHandle
import dagger.hilt.android.lifecycle.HiltViewModel
import team.ppac.common.android.base.BaseViewModel
import team.ppac.splash.mvi.SplashIntent
import team.ppac.splash.mvi.SplashSideEffect
import team.ppac.splash.mvi.SplashState
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
) : BaseViewModel<SplashState, SplashSideEffect, SplashIntent>(savedStateHandle) {

    override fun createInitialState(savedStateHandle: SavedStateHandle): SplashState {
        return SplashState
    }

    override suspend fun handleIntent(intent: SplashIntent) {}
}