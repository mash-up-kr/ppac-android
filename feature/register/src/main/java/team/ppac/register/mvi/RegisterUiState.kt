package team.ppac.register.mvi

import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf
import team.ppac.common.android.base.UiState
import team.ppac.register.model.RegisterCategoryUiModel

data class RegisterUiState(
    val isLoading: Boolean,
    val isError: Boolean,
    val registerCategories: ImmutableList<RegisterCategoryUiModel>,
    val imageUri: String,
    val title: String,
    val source: String,
) : UiState {

    companion object {
        val INITIAL_STATE = RegisterUiState(
            isLoading = false,
            isError = false,
            registerCategories = persistentListOf(
                RegisterCategoryUiModel(
                    category = "감정",
                    keywords = persistentListOf(
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
                RegisterCategoryUiModel(
                    category = "상황",
                    keywords = persistentListOf(
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
                RegisterCategoryUiModel(
                    category = "콘텐츠",
                    keywords = persistentListOf(
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
                )
            ),
            imageUri = "",
            title = "",
            source = "",
        )
    }
}