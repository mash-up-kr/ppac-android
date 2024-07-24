package team.ppac.mypage

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.collections.immutable.toImmutableList
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import team.ppac.common.android.base.BaseViewModel
import team.ppac.domain.usecase.GetUserRecentMemesUseCase
import team.ppac.domain.usecase.GetUserSavedMemesUseCase
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
    private val getUserSavedMemesUseCase: GetUserSavedMemesUseCase,
    private val getUserRecentMemesUseCase: GetUserRecentMemesUseCase,
) : BaseViewModel<MyPageUiState, MyPageSideEffect, MyPageIntent>(savedStateHandle) {

    init {
        val savedMemes = getUserSavedMemesUseCase().cachedIn(viewModelScope)

        reduce {
            copy(
                savedMemes = savedMemes
            )
        }
    }

    override fun createInitialState(savedStateHandle: SavedStateHandle): MyPageUiState {
        return MyPageUiState.INITIAL_STATE
    }

    override suspend fun handleIntent(intent: MyPageIntent) {
        when (intent) {
            is MyPageIntent.ClickRecentMemeItem -> navigateToDetail(intent.memeId)
            is MyPageIntent.ClickSavedMemeItem -> navigateToDetail(intent.memeId)
            MyPageIntent.ClickSettingButton -> navigateToSetting()
            MyPageIntent.InitView -> initialAction()
            MyPageIntent.RefreshData -> refreshAction()
        }
    }

    private fun navigateToDetail(memeId: String) {
        postSideEffect(MyPageSideEffect.NavigateToDetail(memeId = memeId))
    }

    private fun navigateToSetting() {
        postSideEffect(MyPageSideEffect.NavigateToSetting)
    }

    private fun initialAction() {
        reduce { copy(isLoading = true) }
        getUserData()
        reduce { copy(isLoading = false) }
    }

    private fun refreshAction() {
        reduce { copy(isRefreshing = true) }
        getUserData()
        reduce { copy(isRefreshing = false) }
    }

    private fun getUserData() {
        viewModelScope.launch {
            val userDeferred = async {
                getUserUseCase()
            }
            val recentMemesDeferred = async {
                getUserRecentMemesUseCase()
            }

            val user = userDeferred.await()
            val recentMemes = recentMemesDeferred.await()

            reduce {
                copy(
                    levelUiModel = user.toLevelUiModel(),
                    recentMemes = recentMemes.toImmutableList(),
                )
            }
            delay(500L)
        }
    }
}