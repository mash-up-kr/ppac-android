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
        initialAction()
    }

    override fun createInitialState(savedStateHandle: SavedStateHandle): MyPageUiState {
        return MyPageUiState.INITIAL_STATE
    }

    override suspend fun handleIntent(intent: MyPageIntent) {
        when (intent) {
            is MyPageIntent.ClickRecentMemeItem -> navigateToDetail(intent.memeId)
            is MyPageIntent.ClickSavedMemeItem -> navigateToDetail(intent.memeId)
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

    private fun initialAction() {
        viewModelScope.launch {
            reduce { copy(isLoading = true) }
            val userDeferred = async {
                getUserUseCase()
            }
            val recentMemesDeferred = async {
                getUserRecentMemesUseCase()
            }

            val user = userDeferred.await()
            val savedMemes = getUserSavedMemesUseCase().cachedIn(viewModelScope)
            val recentMemes = recentMemesDeferred.await()

            reduce {
                copy(
                    levelUiModel = user.toLevelUiModel(),
                    savedMemes = savedMemes,
                    recentMemes = recentMemes.toImmutableList(),
                )
            }
            reduce { copy(isLoading = false) }
        }
    }

    private fun refreshData() {
        viewModelScope.launch {
            reduce { copy(isRefreshing = true) }
            refreshAction()
            delay(500L)
            reduce { copy(isRefreshing = false) }
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
}