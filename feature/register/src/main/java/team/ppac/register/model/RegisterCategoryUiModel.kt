package team.ppac.register.model

import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf

data class RegisterCategoryUiModel(
    val category: String = "",
    val keywords: ImmutableList<String> = persistentListOf(),
) {
    companion object {
        val INITIAL_STATE
            get() = RegisterCategoryUiModel()
    }
}