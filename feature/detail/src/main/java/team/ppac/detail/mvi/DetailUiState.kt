package team.ppac.detail.mvi

import kotlinx.collections.immutable.persistentListOf
import team.ppac.common.android.base.UiState
import team.ppac.detail.model.DetailMemeUiModel

data class DetailUiState(
    val memeId: String,
    val detailMemeUiModel: DetailMemeUiModel,
    val isError: Boolean,
    val isLoading: Boolean,
    val showOptionBottomSheet: Boolean,
) : UiState {

    companion object {
        val INITIAL_STATE = DetailUiState(
            memeId = "",
            detailMemeUiModel = DetailMemeUiModel(
                imageUrl = "",
                name = "",
                hashTags = persistentListOf(),
                sourceDescription = "",
                isSavedMeme = false,
                reactionCount = -1,
                isReaction = false,
            ),
            isError = false,
            isLoading = false,
            showOptionBottomSheet = false,
        )

        val PREVIEW_STATE = DetailUiState(
            memeId = "",
            detailMemeUiModel = DetailMemeUiModel(
                imageUrl = "",
                name = "나는 공부를 찢어",
                hashTags = persistentListOf("공부", "학생", "시험기간", "힘듦", "피곤"),
                sourceDescription = "출처에 대한 내용이 들어갑니다.",
                isSavedMeme = false,
                reactionCount = 0,
                isReaction = false,
            ),
            isError = false,
            isLoading = false,
            showOptionBottomSheet = false,
        )
    }
}


