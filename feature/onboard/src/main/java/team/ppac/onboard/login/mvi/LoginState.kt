package team.ppac.onboard.login.mvi

import team.ppac.base.UiState

data class LoginState(
    val isLoading: Boolean = true,
): UiState