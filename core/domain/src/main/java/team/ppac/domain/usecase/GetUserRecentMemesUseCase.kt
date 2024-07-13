package team.ppac.domain.usecase

import team.ppac.domain.model.Meme
import team.ppac.domain.repository.UserRepository
import javax.inject.Inject

interface GetUserRecentMemesUseCase {
    suspend fun invoke(): List<Meme>
}

internal class GetUserRecentMemesUseCaseImpl @Inject constructor(
    private val userRepository: UserRepository,
) : GetUserRecentMemesUseCase {
    override suspend fun invoke(): List<Meme> {
        return userRepository.getUserRecentMemes()
    }
}