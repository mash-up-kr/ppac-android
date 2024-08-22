package team.ppac.splash.mvi

import team.ppac.common.android.base.UiState

data class SplashState(
    val isNetworkError: Boolean = false,
) : UiState