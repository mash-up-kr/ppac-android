package team.ppac.sample.mvi

import team.ppac.base.UiState
import team.ppac.domain.model.SampleImageModel

data class SampleState(
    val isLoading: Boolean = true,
    val images: List<SampleImageModel> = emptyList(),
) : UiState