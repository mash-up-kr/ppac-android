package team.ppac.domain.usecase

import team.ppac.domain.repository.MemeRepository
import javax.inject.Inject

interface DeleteSavedMemeUseCase {
    suspend operator fun invoke(memeId: String): Boolean
}

internal class DeleteSavedMemeUseCaseImpl @Inject constructor(
    private val memeRepository: MemeRepository,
) : DeleteSavedMemeUseCase {
    override suspend fun invoke(memeId: String): Boolean =
        memeRepository.deleteSavedMeme(memeId)
}