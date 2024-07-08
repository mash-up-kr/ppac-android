package team.ppac.detail.mvi

import kotlinx.collections.immutable.persistentListOf
import team.ppac.common.android.base.UiState
import team.ppac.detail.model.DetailMemeUiModel

data class DetailUiState(
    val detailMemeUiModel: DetailMemeUiModel
) : UiState {

    companion object {
        val INITIAL_STATE = DetailUiState(
            detailMemeUiModel = DetailMemeUiModel(
                imageUrl = "",
                name = "",
                hashTags = persistentListOf(),
                sourceDescription = ""
            )
        )
    }
}


