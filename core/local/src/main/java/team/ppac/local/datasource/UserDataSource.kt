package team.ppac.local.datasource

import kotlinx.coroutines.flow.Flow
import team.ppac.local.entity.UserData

interface UserDataSource {
    val userDataFlow: Flow<UserData>
    suspend fun setUser(user: UserData)
}