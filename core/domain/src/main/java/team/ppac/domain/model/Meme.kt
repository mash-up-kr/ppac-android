package team.ppac.domain.model

data class Meme(
    val id: String,
    val imageUrl: String,
    val isTodayMeme: Boolean,
    val source: String,
    val title: String,
    val watch: Int,
    val reaction: Int,
    val createdAt: String,
    val updateAt: String,
    val keywords: List<Keyword>,
)
