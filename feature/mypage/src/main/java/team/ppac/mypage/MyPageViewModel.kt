package team.ppac.mypage

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.collections.immutable.toImmutableList
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import team.ppac.common.android.base.BaseViewModel
import team.ppac.domain.usecase.GetLevelUseCase
import team.ppac.domain.usecase.GetUserRecentMemesUseCase
import team.ppac.domain.usecase.GetUserSavedMemesUseCase
import team.ppac.domain.usecase.GetUserUseCase
import team.ppac.domain.usecase.RefreshEventUseCase
import team.ppac.domain.usecase.SetLevelUseCase
import team.ppac.errorhandling.FarmemeNetworkException
import team.ppac.mypage.mapper.toLevelUiModel
import team.ppac.mypage.mvi.MyPageIntent
import team.ppac.mypage.mvi.MyPageSideEffect
import team.ppac.mypage.mvi.MyPageUiState
import javax.inject.Inject

@HiltViewModel
class MyPageViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val getUserUseCase: GetUserUseCase,
    getUserSavedMemesUseCase: GetUserSavedMemesUseCase,
    private val getUserRecentMemesUseCase: GetUserRecentMemesUseCase,
    private val setLevelUseCase: SetLevelUseCase,
    private val getLevelUseCase: GetLevelUseCase,
    refreshEventUseCase: RefreshEventUseCase,
) : BaseViewModel<MyPageUiState, MyPageSideEffect, MyPageIntent>(savedStateHandle) {
    val savedMemeEventFlow = refreshEventUseCase()

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

    override fun handleClientException(throwable: Throwable) {
        if (throwable is FarmemeNetworkException) {
            reduce {
                copy(isError = true)
            }
        }
    }

    override suspend fun handleIntent(intent: MyPageIntent) {
        when (intent) {
            is MyPageIntent.ClickRecentMemeItem -> navigateToDetail(intent.memeId)
            is MyPageIntent.ClickSavedMemeItem -> navigateToDetail(intent.memeId)
            MyPageIntent.ClickSettingButton -> navigateToSetting()
            is MyPageIntent.ClickRetryButton -> {
                initialAction()
                reduce {
                    copy(
                        isError = false,
                    )
                }
            }

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
        launch {
            getUserData()
            delay(500L)
            reduce { copy(isLoading = false) }
        }
    }

    private fun refreshAction() {
        launch {
            reduce { copy(isRefreshing = true) }
            getUserData()
            delay(500L)
            reduce { copy(isRefreshing = false) }
        }
    }

    private suspend fun getUserData() {
        launch {
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

            val lastLevel = getLevelUseCase()
            val currentLevel = user.levelCount

            if (lastLevel < currentLevel) {
                postSideEffect(MyPageSideEffect.ShowLevelUpSnackBar(currentLevel))
                setLevelUseCase(currentLevel)
            }
        }.join()
    }
}