package team.ppac.register

import androidx.lifecycle.SavedStateHandle
import dagger.hilt.android.lifecycle.HiltViewModel
import team.ppac.common.android.base.BaseViewModel
import team.ppac.errorhandling.FarmemeNetworkException
import team.ppac.register.mvi.RegisterIntent
import team.ppac.register.mvi.RegisterSideEffect
import team.ppac.register.mvi.RegisterUiState
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
) : BaseViewModel<RegisterUiState, RegisterSideEffect, RegisterIntent>(savedStateHandle) {

    override fun createInitialState(savedStateHandle: SavedStateHandle): RegisterUiState {
        return RegisterUiState.INITIAL_STATE
    }

    override fun handleClientException(throwable: Throwable) {
        if (throwable is FarmemeNetworkException) {
            reduce {
                copy(isError = true)
            }
        }
    }

    override suspend fun handleIntent(intent: RegisterIntent) {
        when (intent) {
            is RegisterIntent.SetImageFromGallery -> {
                reduce {
                    copy(imageUri = intent.uri)
                }
            }

            is RegisterIntent.InputSource -> {
                reduce {
                    copy(source = intent.source)
                }
            }

            is RegisterIntent.InputTitle -> {
                reduce {
                    copy(title = intent.title)
                }
            }
        }
    }
}