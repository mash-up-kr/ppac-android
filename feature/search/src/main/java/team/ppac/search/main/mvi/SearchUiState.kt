package team.ppac.search.main.mvi

import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf
import team.ppac.common.android.base.UiState
import team.ppac.search.main.model.CategoryUiModel
import team.ppac.search.main.model.HotKeywordUiModel

data class SearchUiState(
    val isLoading: Boolean,
    val showServiceOpenDialog: Boolean,
    val hotKeywords: ImmutableList<HotKeywordUiModel>,
    val memeCategories: ImmutableList<CategoryUiModel>,
) : UiState {

    companion object {
        val INITIAL_STATE = SearchUiState(
            isLoading = false,
            showServiceOpenDialog = false,
            hotKeywords = persistentListOf(
                HotKeywordUiModel(
                    description = "키워드가 길어지면 말줄임",
                    imageUrl = ""
                ),
                HotKeywordUiModel(
                    description = "출근",
                    imageUrl = ""
                ),
                HotKeywordUiModel(
                    description = "슬픈",
                    imageUrl = ""
                ),
                HotKeywordUiModel(
                    description = "직장인",
                    imageUrl = ""
                ),
            ),
            memeCategories = persistentListOf(
                CategoryUiModel(
                    categoryHeader = "감정",
                    categories = persistentListOf(
                        "행복",
                        "슬픈",
                        "분노",
                        "웃긴",
                        "피곤",
                        "절망",
                        "현타",
                        "당황",
                        "무념무상",
                    ),
                ),
                CategoryUiModel(
                    categoryHeader = "감정",
                    categories = persistentListOf(
                        "행복",
                        "슬픈",
                        "분노",
                        "웃긴",
                        "피곤",
                        "절망",
                        "현타",
                        "당황",
                        "무념무상",
                    ),
                ),
                CategoryUiModel(
                    categoryHeader = "감정",
                    categories = persistentListOf(
                        "행복",
                        "슬픈",
                        "분노",
                        "웃긴",
                        "피곤",
                        "절망",
                        "현타",
                        "당황",
                        "무념무상",
                    ),
                ),
                CategoryUiModel(
                    categoryHeader = "감정",
                    categories = persistentListOf(
                        "행복",
                        "슬픈",
                        "분노",
                        "웃긴",
                        "피곤",
                        "절망",
                        "현타",
                        "당황",
                        "무념무상",
                    ),
                ),
                CategoryUiModel(
                    categoryHeader = "감정",
                    categories = persistentListOf(
                        "행복",
                        "슬픈",
                        "분노",
                        "웃긴",
                        "피곤",
                        "절망",
                        "현타",
                        "당황",
                        "무념무상",
                    ),
                ),
            )
        )
    }
}