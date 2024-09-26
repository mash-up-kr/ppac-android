package team.ppac.register.mvi

import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf
import team.ppac.common.android.base.UiState
import team.ppac.register.model.RegisterCategoryUiModel

data class RegisterUiState(
    val isLoading: Boolean,
    val isError: Boolean,
    val registerCategories: ImmutableList<RegisterCategoryUiModel>
) : UiState {

    companion object {
        val INITIAL_STATE = RegisterUiState(
            isLoading = false,
            isError = false,
            registerCategories = persistentListOf(),
        )
    }
}