package team.ppac.detail.model

import kotlinx.collections.immutable.ImmutableList

data class DetailMemeUiModel(
    val imageUrl: String,
    val name: String,
    val hashTags: ImmutableList<String>,
    val sourceDescription: String,
    val isSavedMeme: Boolean,
)
