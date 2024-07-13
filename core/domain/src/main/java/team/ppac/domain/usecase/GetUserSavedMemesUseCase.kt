package team.ppac.domain.usecase

import team.ppac.domain.model.Meme
import team.ppac.domain.repository.UserRepository
import javax.inject.Inject

interface GetUserSavedMemesUseCase {
    suspend fun invoke(): List<Meme>
}

internal class GetUserSavedMemesUseCaseImpl @Inject constructor(
    private val userRepository: UserRepository,
) : GetUserSavedMemesUseCase {
    override suspend fun invoke(): List<Meme> {
        return userRepository.getUserSavedMemes()
    }
}