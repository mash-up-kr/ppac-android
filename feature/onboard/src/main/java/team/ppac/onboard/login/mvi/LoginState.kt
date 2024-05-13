package team.ppac.onboard.login.mvi

import team.ppac.base.UiState

data class LoginState(
    val isLoading: Boolean = true,
    val phoneNumber: String = "",
    val nickname: String = "",
    val toastVisible: Boolean = false,
) : UiState
