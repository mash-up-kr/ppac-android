package team.ppac.local.entity

import kotlinx.serialization.Serializable

@Serializable
data class UserData(
    val userId: String,
) {
    companion object {
        val EMPTY
            get() = UserData(
                userId = "",
            )
    }
}
