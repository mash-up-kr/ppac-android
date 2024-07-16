package team.ppac.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.firstOrNull
import team.ppac.data.mapper.toMeme
import team.ppac.data.mapper.toUser
import team.ppac.data.paging.SavedMemesPagingSource
import team.ppac.datastore.entity.UserData
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

        return if (user != UserData.EMPTY) {
            true
        } else { // 유저 API로 등록 후 로컬에 등록 여부 저장
            val deviceId = appConfig.deviceId
            val userResponse = userRemoteDataSource.postUser(deviceId)
            userLocalDataSource.setUser(user = UserData(userResponse.deviceId))
            false
        }
    }

    override suspend fun getUser(): User {
        return userRemoteDataSource.getUser().toUser()
    }

    override suspend fun getUserSavedMemes(): Flow<PagingData<Meme>> {
        return Pager(
            config = PagingConfig(
                pageSize = SAVED_MEME_PAGING_PAGE,
                enablePlaceholders = true,
            ),
            pagingSourceFactory = {
                SavedMemesPagingSource(
                    getSavedMemes = { page ->
                        userRemoteDataSource.getUserSavedMemes(
                            page = page,
                            size = SAVED_MEME_PAGING_PAGE,
                        ).memeList.map { it.toMeme() }
                    },
                )
            },
        ).flow
    }

    override suspend fun getUserRecentMemes(): List<Meme> {
        return userRemoteDataSource.getUserRecentMemes().map { it.toMeme() }
    }

    internal companion object {
        const val SAVED_MEME_PAGING_PAGE = 10
    }
}