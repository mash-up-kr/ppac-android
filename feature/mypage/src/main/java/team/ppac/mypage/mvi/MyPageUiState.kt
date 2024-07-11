package team.ppac.mypage.mvi

import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.toPersistentList
import team.ppac.common.android.base.UiState
import team.ppac.mypage.model.LeveInfo
import team.ppac.mypage.model.MyPageLevel
import team.ppac.mypage.model.RecentMemeUiModel

data class MyPageUiState(
    val isLoading: Boolean,
    val leveInfo: LeveInfo,
    val recentMemes: ImmutableList<RecentMemeUiModel>,
    val savedMemes: ImmutableList<RecentMemeUiModel>,
) : UiState {
    companion object {
        const val sampleId = "1234"
        const val sampleUrl = "https://picsum.photos/id/10/2500/1667"
        const val sampleTitle = "title"
        val sampleMemes = mutableListOf<RecentMemeUiModel>().apply {
            repeat(11) {
                this.add(
                    RecentMemeUiModel(
                        id = sampleId,
                        imageUrl = sampleUrl,
                        title = sampleTitle,
                    )
                )
            }
        }.toPersistentList()

        val INITIAL_STATE = MyPageUiState(
            isLoading = false,
            leveInfo = LeveInfo(
                userLevel = MyPageLevel.LEVEL1,
                memeCount = 0,
            ),
            recentMemes = sampleMemes,
            savedMemes = sampleMemes,
        )
    }
}