package team.ppac.detail.mvi

import kotlinx.collections.immutable.persistentListOf
import team.ppac.common.android.base.UiState
import team.ppac.detail.model.DetailMemeUiModel

data class DetailUiState(
    val memeId: String,
    val detailMemeUiModel: DetailMemeUiModel,
    val isError: Boolean,
) : UiState {

    companion object {
        val INITIAL_STATE = DetailUiState(
            memeId = "",
            detailMemeUiModel = DetailMemeUiModel(
                imageUrl = "",
                name = "나느 공부를 찢어 나는 공부를 찢어 나는 공부를 찢어",
                hashTags = persistentListOf("공부", "배고파", "졸려", "심심해", "우울", "힘듦", "배고파"),
                sourceDescription = "이곳에는 출처에 대한 내용이 들어갑니다. 이곳에는 출처에 대한 내용이 들어갑니다.",
                isSavedMeme = false,
                reactionCount = 0,
            ),
            isError = false,
        )
    }
}


