package team.ppac.register.model

import kotlinx.collections.immutable.ImmutableList

data class RegisterCategoryUiModel(
    val category: String,
    val keywords: ImmutableList<String>,
)