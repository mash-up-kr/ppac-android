package team.ppac.sample

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import team.ppac.common.android.base.BaseViewModel
import team.ppac.domain.usecase.SampleUseCase
import team.ppac.sample.mvi.SampleIntent
import team.ppac.sample.mvi.SampleSideEffect
import team.ppac.sample.mvi.SampleState
import javax.inject.Inject

@HiltViewModel
class SampleViewModel @Inject constructor(
    private val sampleUseCase: SampleUseCase,
) : BaseViewModel<SampleState, SampleSideEffect, SampleIntent>(SampleState()) {
    override suspend fun handleIntent(intent: SampleIntent) {
        when (intent) {
            is SampleIntent.ClickGetImagesButton -> getImages()
        }
    }

    private fun updateLoading(isLoading: Boolean) {
        reduce {
            copy(isLoading = isLoading)
        }
    }

    private fun getImages() {
        viewModelScope.launch {
            updateLoading(true)
            val images = sampleUseCase.getImages()
            reduce {
                copy(images = images)
            }
            updateLoading(false)
        }
    }
}