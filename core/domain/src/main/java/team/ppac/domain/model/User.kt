package team.ppac.domain.model

data class User(
    val id: String,
    val isDeleted: Boolean,
    val lastSeenMeme: List<String>,
    val level: Int,
    val reaction: Int,
    val save: Int,
    val share: Int,
    val watch: Int
)