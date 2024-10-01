package team.ppac.domain.usecase

import team.ppac.domain.repository.MemeRepository
import javax.inject.Inject

interface ShareMemeUseCase {
    suspend operator fun invoke(memeId: String): Boolean
}

internal class ShareMemeUseCaseImpl @Inject constructor(
    private val memeRepository: MemeRepository,
) : ShareMemeUseCase {
    override suspend fun invoke(memeId: String): Boolean =
        memeRepository.shareMeme(memeId)

}