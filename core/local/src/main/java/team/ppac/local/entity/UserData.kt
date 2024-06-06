package team.ppac.local.entity

import kotlinx.serialization.Serializable

@Serializable
data class UserData(
    val userId: Long,
) {
    companion object {
        val EMPTY get() = UserData(userId = -1L)
    }
}
