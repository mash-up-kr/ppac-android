package team.ppac.setting

import android.content.Context
import androidx.lifecycle.SavedStateHandle
import dagger.hilt.android.lifecycle.HiltViewModel
import team.ppac.common.android.base.BaseViewModel
import team.ppac.common.android.util.launchGooglePlayStore
import team.ppac.common.android.util.needToUpdateVersion
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
            is SettingIntent.InitView -> checkToUpdateVersion(intent.context)
            SettingIntent.ClickBackButton -> onBackButtonClick()
            SettingIntent.ClickPrivacyPolicy -> postSideEffect(SettingSideEffect.NavigateToPrivacyPolicy)
            is SettingIntent.ClickAppUpdateButton -> {
                if (launchGooglePlayStore(intent.context)) {
                    postSideEffect(SettingSideEffect.UpdateApp)
                }
            }
            is SettingIntent.CheckNewAppVersionAvailable -> reduce {
                copy(hasNewAppVersion = intent.hasNewAppVersion)
            }
        }
    }

    private fun checkToUpdateVersion(context: Context) {
        val needToUpdateVersion = needToUpdateVersion(context)
        reduce {
            copy(hasNewAppVersion = needToUpdateVersion)
        }
    }

    private fun onBackButtonClick() {
        postSideEffect(SettingSideEffect.NavigateToBack)
    }
}