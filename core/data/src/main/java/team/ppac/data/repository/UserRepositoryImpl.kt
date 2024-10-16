package team.ppac.data.repository

import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.firstOrNull
import team.ppac.core.datastore.UserData
import team.ppac.data.mapper.toMeme
import team.ppac.data.mapper.toUser
import team.ppac.data.paging.ITEMS_PER_PAGE
import team.ppac.data.paging.createPager
import team.ppac.domain.model.Meme
import team.ppac.domain.model.User
import team.ppac.domain.repository.UserRepository
import team.ppac.local.datasource.AppConfig
import team.ppac.local.datasource.UserLocalDataSource
import team.ppac.remote.datasource.UserRemoteDataSource
import javax.inject.Inject

internal class UserRepositoryImpl @Inject constructor(
    private val userRemoteDataSource: UserRemoteDataSource,
    private val userLocalDataSource: UserLocalDataSource,
    private val appConfig: AppConfig,
) : UserRepository {
    override suspend fun createUser(): Boolean {
        val user = userLocalDataSource.userDataFlow.firstOrNull()

        return if (user != UserData.getDefaultInstance()) {
            true
        } else { // 유저 API로 등록 후 로컬에 등록 여부 저장
            val deviceId = appConfig.deviceId
            val userResponse = userRemoteDataSource.postUser(deviceId)
            userLocalDataSource.setUser {
                UserData.newBuilder()
                    .setUserId(userResponse.deviceId)
                    .setLevel(userResponse.level)
                    .build()
            }
            false
        }
    }

    override suspend fun getUser(): User {
        return userRemoteDataSource.getUser().toUser()
    }

    override fun getUserSavedMemes(getCurrentPage: (Int) -> Unit): Flow<PagingData<Meme>> {
        return createPager(
            getCurrentPage = getCurrentPage
        ) { page ->
            userRemoteDataSource.getUserSavedMemes(
                page = page,
                size = ITEMS_PER_PAGE,
            ).memeList.map { it.toMeme() }
        }.flow
    }

    override fun getUserRegisteredMemes(): Flow<PagingData<Meme>> {
        return createPager { page ->
            userRemoteDataSource.getUserRegisteredMemes(
                page = page,
                size = ITEMS_PER_PAGE,
            ).memeList.map { it.toMeme() }
        }.flow
    }

    override suspend fun getUserRecentMemes(): List<Meme> {
        return userRemoteDataSource.getUserRecentMemes().map { it.toMeme() }
    }

    override suspend fun setLevel(level: Int) {
        userLocalDataSource.setUser { userData ->
            userData.toBuilder()
                .setLevel(level)
                .build()
        }
    }

    override suspend fun getLevel(): Int {
        return userLocalDataSource.userDataFlow.firstOrNull()?.level ?: 1
    }
}