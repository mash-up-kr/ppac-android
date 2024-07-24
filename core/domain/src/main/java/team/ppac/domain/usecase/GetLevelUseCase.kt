package team.ppac.domain.usecase

import team.ppac.domain.repository.UserRepository
import javax.inject.Inject

interface GetLevelUseCase {
    suspend operator fun invoke(): Int
}

internal class GetLevelUseCaseImpl @Inject constructor(
    private val userRepository: UserRepository,
) : GetLevelUseCase {
    override suspend fun invoke(): Int {
        return userRepository.getLevel()
    }
}