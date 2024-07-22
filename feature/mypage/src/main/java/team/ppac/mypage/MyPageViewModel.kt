package team.ppac.mypage

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
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
        initialAction()
    }

    private fun initialAction() {
        viewModelScope.launch {
            updateLoading(true)
            val userDeferred = async {
                getUserUseCase()
            }
            val recentMemesDeferred = async {
                getUserRecentMemesUseCase()
            }

            val user = userDeferred.await()
            val savedMemes = getUserSavedMemesUseCase()
            val recentMemes = recentMemesDeferred.await()

            reduce {
                copy(
                    levelUiModel = user.toLevelUiModel(),
                    savedMemes = savedMemes,
                    recentMemes = recentMemes.toImmutableList(),
                )
            }
            updateLoading(false)
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

            is MyPageIntent.RefreshData -> refreshData()
        }
    }

    private fun navigateToDetail(memeId: String) {
        postSideEffect(MyPageSideEffect.NavigateToDetail(memeId = memeId))
    }

    private fun navigateToSetting() {
        postSideEffect(MyPageSideEffect.NavigateToSetting)
    }

    private fun refreshData() {
        viewModelScope.launch {
            updateLoading(true)
            refreshAction()
            delay(1_000L)
            updateLoading(false)
        }
    }

    private fun refreshAction() {
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
        }
    }

    private fun updateLoading(isLoading: Boolean) {
        reduce {
            copy(isLoading = isLoading)
        }
    }
}