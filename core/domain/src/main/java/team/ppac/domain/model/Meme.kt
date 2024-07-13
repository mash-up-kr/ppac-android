package team.ppac.domain.model

import kotlinx.collections.immutable.ImmutableList

data class Meme(
    val id: String,
    val image: String,
    val isTodayMeme: Boolean,
    val source: String,
    val title: String,
    val watch: Int,
    val reaction: Int,
    val createdAt: String,
    val updateAt: String,
    val keywords: ImmutableList<Keyword>,
)
