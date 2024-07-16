package team.ppac.domain.usecase

import team.ppac.domain.repository.MemeRepository
import javax.inject.Inject

interface SaveMemeUseCase {
    suspend operator fun invoke(memeId: String): Boolean
}

internal class SaveMemeUseCaseImpl @Inject constructor(
    private val memeRepository: MemeRepository,
) : SaveMemeUseCase {
    override suspend fun invoke(memeId: String): Boolean =
        memeRepository.saveMeme(memeId)
}