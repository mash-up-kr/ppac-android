package team.ppac.remote.datasource

interface UserRemoteDataSource {
    suspend fun postUser(deviceId: String)
}