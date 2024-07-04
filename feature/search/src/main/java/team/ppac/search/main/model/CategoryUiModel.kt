package team.ppac.search.main.model

import kotlinx.collections.immutable.ImmutableList

data class CategoryUiModel(
    val categoryHeader: String,
    val categories: ImmutableList<String>,
)
