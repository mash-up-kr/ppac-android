package team.ppac.domain.usecase

import team.ppac.domain.repository.UserRepository
import javax.inject.Inject

interface UserCreateUseCase {
    suspend operator fun invoke()
}

internal class UserCreateUseCaseImpl @Inject constructor(
    private val userRepository: UserRepository,
) : UserCreateUseCase {
    override suspend fun invoke() {
        userRepository.createUser()
    }
}