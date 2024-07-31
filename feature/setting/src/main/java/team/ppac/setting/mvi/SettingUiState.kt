package team.ppac.setting.mvi

import team.ppac.common.android.base.UiState

data class SettingUiState(
    val isLoading: Boolean,
    val updateButtonVisible: Boolean,
) : UiState {
    companion object {
        val INITIAL_STATE = SettingUiState(
            isLoading = false,
            updateButtonVisible = false,
        )
    }
}