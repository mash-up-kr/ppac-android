package team.ppac.register.mvi

import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.ImmutableSet
import kotlinx.collections.immutable.persistentListOf
import kotlinx.collections.immutable.persistentSetOf
import team.ppac.common.android.base.UiState
import team.ppac.domain.model.Keyword
import team.ppac.register.model.RegisterCategoryUiModel

data class RegisterUiState(
    val isLoading: Boolean,
    val isError: Boolean,
    val registerCategories: ImmutableList<RegisterCategoryUiModel>,
    val selectedKeywords: ImmutableSet<Keyword>,
    val imageUri: String,
    val title: String,
    val source: String,
) : UiState {

    companion object {
        val INITIAL_STATE = RegisterUiState(
            isLoading = false,
            isError = false,
            registerCategories = persistentListOf(),
            selectedKeywords = persistentSetOf(),
            imageUri = "",
            title = "",
            source = "",
        )
    }
}