package team.ppac.domain.usecase

import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import team.ppac.domain.model.Meme
import team.ppac.domain.repository.UserRepository
import javax.inject.Inject

interface GetUserRegisteredMemesUseCase {
    operator fun invoke(getCurrentPage: (Int) -> Unit): Flow<PagingData<Meme>>
}

internal class GetUserRegisteredMemesUseCaseImpl @Inject constructor(
    private val userRepository: UserRepository,
) : GetUserRegisteredMemesUseCase {
    override fun invoke(getCurrentPage: (Int) -> Unit): Flow<PagingData<Meme>> {
        return userRepository.getUserRegisteredMemes(getCurrentPage = getCurrentPage)
    }
}