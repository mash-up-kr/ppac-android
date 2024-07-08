package team.ppac.domain.usecase

import team.ppac.domain.repository.UserRepository
import javax.inject.Inject

interface CreateUserUseCase {
    suspend operator fun invoke()
}

internal class CreateUserUseCaseImpl @Inject constructor(
    private val userRepository: UserRepository,
) : CreateUserUseCase {
    override suspend fun invoke() {
        userRepository.createUser()
    }
}