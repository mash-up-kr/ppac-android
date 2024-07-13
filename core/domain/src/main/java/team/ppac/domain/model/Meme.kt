package team.ppac.domain.model

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
    val keywords: List<String>,
)
