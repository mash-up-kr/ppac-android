package team.ppac.register.model

import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf
import team.ppac.domain.model.Keyword

data class RegisterCategoryUiModel(
    val category: String = "",
    val keywords: ImmutableList<Keyword> = persistentListOf(),
) {
    companion object {
        val INITIAL_STATE
            get() = RegisterCategoryUiModel()
    }
}