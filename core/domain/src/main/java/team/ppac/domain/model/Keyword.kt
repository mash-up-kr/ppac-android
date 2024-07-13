package team.ppac.domain.model

data class Keyword(
    val id: String,
    val name: String,
    val searchCount: Int?,
    val createdAt: String?,
    val updatedAt: String?,
    val category: String?,
    val imageUrl: String?,
)
