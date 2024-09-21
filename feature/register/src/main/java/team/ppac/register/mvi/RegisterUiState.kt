package team.ppac.register.mvi

import team.ppac.common.android.base.UiState

data class RegisterUiState(
    val isLoading: Boolean,
    val isError: Boolean,
) : UiState {

    companion object {
        val INITIAL_STATE = RegisterUiState(
            isLoading = false,
            isError = false,
        )
    }
}