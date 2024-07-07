package team.ppac.detail.mvi

import team.ppac.common.android.base.UiState
import team.ppac.detail.model.DetailMemeUiModel

data class DetailUiState(
    val detailMemeUiModel: DetailMemeUiModel = DetailMemeUiModel("", emptyList(),"")
) : UiState {

    companion object{
        val INITIAL_STATE  = DetailUiState()
    }

}


