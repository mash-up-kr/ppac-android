package team.ppac.local.entity

import kotlinx.serialization.Serializable

@Serializable
data class UserData(
    val userId: Long,
    val name: String,
) {
    companion object {
        val EMPTY
            get() = UserData(
                userId = -1L,
                name = "",
            )
    }
}
