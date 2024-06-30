package team.ppac.recommendation.mvi

import team.ppac.common.android.base.UiState
import team.ppac.domain.model.SampleImageModel

data class RecommendationState(
    val isLoading: Boolean = false,
    val images: List<SampleImageModel> = emptyList(),
) : UiState {
    companion object {
        val INITIAL_STATE
            get() = RecommendationState()
    }
}