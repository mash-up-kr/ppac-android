package team.ppac.mypage

import androidx.lifecycle.SavedStateHandle
import dagger.hilt.android.lifecycle.HiltViewModel
import team.ppac.common.android.base.BaseViewModel
import team.ppac.mypage.mvi.MyPageIntent
import team.ppac.mypage.mvi.MyPageSideEffect
import team.ppac.mypage.mvi.MyPageUiState
import javax.inject.Inject

@HiltViewModel
class MyPageViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
) : BaseViewModel<MyPageUiState, MyPageSideEffect, MyPageIntent>(savedStateHandle) {
    override fun createInitialState(savedStateHandle: SavedStateHandle): MyPageUiState {
        return MyPageUiState.INITIAL_STATE
    }

    override suspend fun handleIntent(intent: MyPageIntent) {
        when (intent) {
            is MyPageIntent.ClickRecentMemeItem, MyPageIntent.ClickSavedMemeItem -> navigateToDetail()
            is MyPageIntent.ClickSettingButton -> navigateToSetting()
            is MyPageIntent.ClickSavedMemeCopyButton -> {}
        }
    }

    private fun navigateToDetail() {
        postSideEffect(MyPageSideEffect.NavigateToDetail)
    }

    private fun navigateToSetting() {
        postSideEffect(MyPageSideEffect.NavigateToSetting)
    }
}