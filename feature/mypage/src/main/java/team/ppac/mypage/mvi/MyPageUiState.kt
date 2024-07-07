package team.ppac.mypage.mvi

import team.ppac.common.android.base.UiState
import team.ppac.mypage.model.Meme
import team.ppac.mypage.model.MyPageLevel
import team.ppac.mypage.model.MyPageUiModel

data class MyPageUiState(
    val isLoading: Boolean,
    val myPageUiModel: MyPageUiModel,
    val recentMemes: List<Meme>,
    val savedMemes: List<Meme>,
) : UiState {
    companion object {
        val INITIAL_STATE = MyPageUiState(
            isLoading = false,
            myPageUiModel = MyPageUiModel(
                userLevel = MyPageLevel.LEVEL1,
                memeCount = 0,
            ),
            recentMemes = emptyList(),
            savedMemes = emptyList(),
        )
    }
}