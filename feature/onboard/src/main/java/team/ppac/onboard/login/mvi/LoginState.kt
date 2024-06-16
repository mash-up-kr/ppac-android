package team.ppac.onboard.login.mvi

import team.ppac.common.android.base.UiState

data class LoginState(
    val isLoading: Boolean = true,
): UiState