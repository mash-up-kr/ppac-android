package team.ppac.local.datasource.impl

import androidx.datastore.core.DataStore
import kotlinx.coroutines.flow.Flow
import team.ppac.local.datasource.UserDataSource
import team.ppac.local.entity.UserData
import javax.inject.Inject

internal class UserDataSourceImpl @Inject constructor(
    private val userDataStore: DataStore<UserData>,
) : UserDataSource {
    override val userDataFlow: Flow<UserData> = userDataStore.data

    override suspend fun setUser(user: UserData) {
        userDataStore.updateData { user }
    }
}