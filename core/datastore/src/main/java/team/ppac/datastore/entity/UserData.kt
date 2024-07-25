package team.ppac.datastore.entity

import kotlinx.serialization.Serializable

@Serializable
data class UserData(
    val userId: String,
    val level: Int,
) {
    companion object {
        val EMPTY
            get() = UserData(
                userId = "",
                level = 1,
            )
    }
}
