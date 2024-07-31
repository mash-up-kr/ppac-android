package team.ppac.setting

import androidx.lifecycle.SavedStateHandle
import dagger.hilt.android.lifecycle.HiltViewModel
import team.ppac.common.android.base.BaseViewModel
import team.ppac.setting.mvi.SettingIntent
import team.ppac.setting.mvi.SettingSideEffect
import team.ppac.setting.mvi.SettingUiState
import javax.inject.Inject

@HiltViewModel
class SettingViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
) : BaseViewModel<SettingUiState, SettingSideEffect, SettingIntent>(savedStateHandle) {
    override fun createInitialState(savedStateHandle: SavedStateHandle): SettingUiState {
        return SettingUiState.INITIAL_STATE
    }

    override fun handleClientException(throwable: Throwable) {

    }

    override suspend fun handleIntent(intent: SettingIntent) {
        when (intent) {
            SettingIntent.ClickBackButton -> onClickBackButton()
            SettingIntent.ClickPrivacyPolicy -> postSideEffect(SettingSideEffect.NavigateToPrivacyPolicy)
            is SettingIntent.UpdateButtonVisible -> reduce {
                copy(updateButtonVisible = intent.isVisible)
            }
        }
    }

    private fun onClickBackButton() {
        postSideEffect(SettingSideEffect.NavigateToBack)
    }
}