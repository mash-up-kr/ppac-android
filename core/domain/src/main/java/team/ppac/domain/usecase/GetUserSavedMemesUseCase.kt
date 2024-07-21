package team.ppac.domain.usecase

import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import team.ppac.domain.model.Meme
import team.ppac.domain.repository.UserRepository
import javax.inject.Inject

interface GetUserSavedMemesUseCase {
    suspend operator fun invoke(): Flow<PagingData<Meme>>
}

internal class GetUserSavedMemesUseCaseImpl @Inject constructor(
    private val userRepository: UserRepository,
) : GetUserSavedMemesUseCase {
    override suspend fun invoke(): Flow<PagingData<Meme>> {
        return userRepository.getUserSavedMemes()
    }
}