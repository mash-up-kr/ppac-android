package team.ppac.mypage

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.collections.immutable.toImmutableList
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import team.ppac.common.android.base.BaseViewModel
import team.ppac.domain.usecase.GetUserRecentMemesUseCase
import team.ppac.domain.usecase.GetUserUseCase
import team.ppac.mypage.mapper.toLevelUiModel
import team.ppac.mypage.mvi.MyPageIntent
import team.ppac.mypage.mvi.MyPageSideEffect
import team.ppac.mypage.mvi.MyPageUiState
import javax.inject.Inject

@HiltViewModel
class MyPageViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val getUserUseCase: GetUserUseCase,
//    private val userSavedMemesUseCase: GetUserSavedMemesUseCase,
    private val userRecentMemesUseCase: GetUserRecentMemesUseCase,
) : BaseViewModel<MyPageUiState, MyPageSideEffect, MyPageIntent>(savedStateHandle) {

    init {
        initialAction()
    }

    private fun initialAction() {
        viewModelScope.launch {
            val userDeferred = async {
                getUserUseCase()
            }
//            val savedMemesDeferred = async {
//                userSavedMemesUseCase()
//            }
            val recentMemesDeferred = async {
                userRecentMemesUseCase()
            }

            val user = userDeferred.await()
//            val savedMemes = savedMemesDeferred.await()
            val recentMemes = recentMemesDeferred.await()

            reduce {
                copy(
                    levelUiModel = user.toLevelUiModel(),
//                    savedMemes = savedMemes.toImmutableList(),
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
            is MyPageIntent.ClickRecentMemeItem -> {
                navigateToDetail(intent.memeId)
            }

            is MyPageIntent.ClickSavedMemeItem -> {
                navigateToDetail(intent.memeId)
            }

            is MyPageIntent.ClickSettingButton -> navigateToSetting()
        }
    }

    private fun navigateToDetail(memeId: String) {
        postSideEffect(MyPageSideEffect.NavigateToDetail(memeId = memeId))
    }

    private fun navigateToSetting() {
        postSideEffect(MyPageSideEffect.NavigateToSetting)
    }
}