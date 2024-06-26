package team.ppac.sample.mvi

import team.ppac.common.android.base.UiState
import team.ppac.domain.model.SampleImageModel

data class SampleState(
    val isLoading: Boolean = false,
    val images: List<SampleImageModel> = emptyList(),
) : UiState