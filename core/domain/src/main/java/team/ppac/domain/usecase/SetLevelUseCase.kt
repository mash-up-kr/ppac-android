package team.ppac.domain.usecase

import team.ppac.domain.repository.UserRepository
import javax.inject.Inject

interface SetLevelUseCase {
    suspend operator fun invoke(level: Int)
}

internal class SetLevelUseCaseImpl @Inject constructor(
    private val userRepository: UserRepository,
) : SetLevelUseCase {
    override suspend fun invoke(level: Int) {
        userRepository.setLevel(level = level)
    }
}