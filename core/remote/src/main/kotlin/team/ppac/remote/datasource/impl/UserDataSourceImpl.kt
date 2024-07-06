package team.ppac.remote.datasource.impl

import team.ppac.remote.datasource.UserRemoteDataSource
import javax.inject.Inject

internal class UserDataSourceImpl @Inject constructor() : UserRemoteDataSource {
    override suspend fun postUser(deviceId: String) {
        TODO("Not yet implemented")
    }
}