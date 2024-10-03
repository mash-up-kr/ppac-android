package team.ppac.splash.mvi

import team.ppac.common.android.base.UiSideEffect

sealed class SplashSideEffect : UiSideEffect {
    data object NavigateToMain : SplashSideEffect()
    data object ForceFinish : SplashSideEffect()
}