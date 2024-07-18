package team.ppac.mypage.mvi

import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.toPersistentList
import team.ppac.common.android.base.UiState
import team.ppac.domain.model.Meme
import team.ppac.mypage.model.LevelUiModel
import team.ppac.mypage.model.MyPageLevel

data class MyPageUiState(
    val isLoading: Boolean,
    val levelUiModel: LevelUiModel,
    val recentMemes: ImmutableList<Meme>,
    val savedMemes: ImmutableList<Meme>,
) : UiState {
    companion object {
        const val sampleId = "1234"
        const val sampleUrl = "https://picsum.photos/id/10/2500/1667"
        const val sampleTitle = "title"
        val sampleMemes = mutableListOf<Meme>().apply {
            repeat(11) {
                this.add(
                    Meme(
                        id = sampleId,
                        imageUrl = sampleUrl,
                        isTodayMeme = false,
                        source = "",
                        title = sampleTitle,
                        watch = 0,
                        reaction = 0,
                        createdAt = "",
                        updateAt = "",
                        keywords = emptyList(),
                    )
                )
            }
        }.toPersistentList()

        val INITIAL_STATE = MyPageUiState(
            isLoading = false,
            levelUiModel = LevelUiModel(
                myPageLevel = MyPageLevel.LEVEL1,
                memeCount = 0,
            ),
            recentMemes = sampleMemes,
            savedMemes = sampleMemes,
        )
    }
}