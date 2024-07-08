package team.ppac.domain.usecase

import team.ppac.domain.model.User
import team.ppac.domain.repository.UserRepository
import javax.inject.Inject

interface GetUserUseCase {
    suspend operator fun invoke(): User
}

internal class GetUserUseCaseImpl @Inject constructor(
    private val userRepository: UserRepository,
) : GetUserUseCase {
    override suspend fun invoke(): User {
        return userRepository.getUser()
    }
}