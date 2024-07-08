package team.ppac.datastore.entity

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
