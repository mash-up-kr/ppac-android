package team.ppac.mypage.mvi

import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf
import team.ppac.common.android.base.UiState
import team.ppac.mypage.model.RecentMemeUiModel
import team.ppac.mypage.model.MyPageLevel
import team.ppac.mypage.model.MyPageUiModel

data class MyPageUiState(
    val isLoading: Boolean,
    val myPageUiModel: MyPageUiModel,
    val recentMemes: ImmutableList<RecentMemeUiModel>,
    val savedMemes: ImmutableList<RecentMemeUiModel>,
) : UiState {
    companion object {
        const val sampleId = "1234"
        const val sampleUrl = "https://picsum.photos/id/10/2500/1667"

        val INITIAL_STATE = MyPageUiState(
            isLoading = false,
            myPageUiModel = MyPageUiModel(
                userLevel = MyPageLevel.LEVEL1,
                memeCount = 0,
            ),
            recentMemes = persistentListOf<RecentMemeUiModel>().add(
                RecentMemeUiModel(
                    id = sampleId,
                    imageUrl = sampleUrl,
                ),
            ).add(
                RecentMemeUiModel(
                    id = sampleId,
                    imageUrl = sampleUrl,
                ),
            ).add(
                RecentMemeUiModel(
                    id = sampleId,
                    imageUrl = sampleUrl,
                ),
            ).add(
                RecentMemeUiModel(
                    id = sampleId,
                    imageUrl = sampleUrl,
                ),
            ).add(
                RecentMemeUiModel(
                    id = sampleId,
                    imageUrl = sampleUrl,
                ),
            ),
            savedMemes = persistentListOf(),
        )
    }
}