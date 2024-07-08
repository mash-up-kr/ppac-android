package team.ppac.domain.model

data class User(
    val id: String,
    val isDeleted: Boolean,
    val lastSeenMeme: List<String>,
    val levelCount: Int,
    val reactionCount: Int,
    val saveCount: Int,
    val shareCount: Int,
    val watchCount: Int
)