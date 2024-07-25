package team.ppac.local.datasource

import kotlinx.coroutines.flow.Flow
import team.ppac.datastore.entity.UserData

interface UserLocalDataSource {
    val userDataFlow: Flow<UserData>
    suspend fun setUser(transform: (UserData) -> UserData)
}