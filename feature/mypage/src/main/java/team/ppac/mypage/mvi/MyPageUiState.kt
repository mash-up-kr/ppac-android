package team.ppac.mypage.mvi

import androidx.paging.PagingData
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import team.ppac.common.android.base.UiState
import team.ppac.domain.model.Meme
import team.ppac.mypage.model.LevelUiModel

data class MyPageUiState(
    val isLoading: Boolean = true,
    val isRefreshing: Boolean = false,
    val levelUiModel: LevelUiModel = LevelUiModel(),
    val recentMemes: ImmutableList<Meme> = persistentListOf(),
    val savedMemes: Flow<PagingData<Meme>> = flowOf(PagingData.empty()),
) : UiState {
    companion object {
        val INITIAL_STATE
            get() = MyPageUiState()
    }
}