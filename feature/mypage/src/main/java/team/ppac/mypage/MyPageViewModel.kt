package team.ppac.mypage

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.collections.immutable.toImmutableList
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import team.ppac.common.android.base.BaseViewModel
import team.ppac.domain.usecase.GetUserRecentMemesUseCase
import team.ppac.domain.usecase.GetUserSavedMemesUseCase
import team.ppac.mypage.mvi.MyPageIntent
import team.ppac.mypage.mvi.MyPageSideEffect
import team.ppac.mypage.mvi.MyPageUiState
import javax.inject.Inject

@HiltViewModel
class MyPageViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val getUserSavedMemesUseCase: GetUserSavedMemesUseCase,
    private val getUserRecentMemesUseCase: GetUserRecentMemesUseCase,
) : BaseViewModel<MyPageUiState, MyPageSideEffect, MyPageIntent>(savedStateHandle) {

    init {
        getMemes()
    }

    private fun getMemes() {
        viewModelScope.launch {
            val savedMemesDeferred = async {
                getUserSavedMemesUseCase()
            }
            val recentMemesDeferred = async {
                getUserRecentMemesUseCase()
            }

            val savedMemes = savedMemesDeferred.await()
            val recentMemes = recentMemesDeferred.await()

            reduce {
                copy(
                    savedMemes = savedMemes,
                    recentMemes = recentMemes.toImmutableList(),
                )
            }
        }
    }

    override fun createInitialState(savedStateHandle: SavedStateHandle): MyPageUiState {
        return MyPageUiState.INITIAL_STATE
    }

    override suspend fun handleIntent(intent: MyPageIntent) {
        when (intent) {
            is MyPageIntent.ClickRecentMemeItem, MyPageIntent.ClickSavedMemeItem -> navigateToDetail()
            is MyPageIntent.ClickSettingButton -> navigateToSetting()
        }
    }

    private fun navigateToDetail() {
        postSideEffect(MyPageSideEffect.NavigateToDetail)
    }

    private fun navigateToSetting() {
        postSideEffect(MyPageSideEffect.NavigateToSetting)
    }
}