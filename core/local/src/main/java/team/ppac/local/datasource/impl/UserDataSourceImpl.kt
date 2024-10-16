package team.ppac.local.datasource.impl

import androidx.datastore.core.DataStore
import kotlinx.coroutines.flow.Flow
import team.ppac.core.datastore.UserData
import team.ppac.local.datasource.UserLocalDataSource
import javax.inject.Inject

internal class UserDataSourceImpl @Inject constructor(
    private val userDataStore: DataStore<UserData>,
) : UserLocalDataSource {
    override val userDataFlow: Flow<UserData> = userDataStore.data

    override suspend fun setUser(transform: (UserData) -> UserData) {
        userDataStore.updateData(transform)
    }
}