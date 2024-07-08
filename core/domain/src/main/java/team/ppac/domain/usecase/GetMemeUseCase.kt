package team.ppac.domain.usecase

import team.ppac.domain.model.Meme
import team.ppac.domain.repository.MemeRepository
import javax.inject.Inject

interface GetMemeUseCase {
    suspend operator fun invoke(memeId: String): Meme
}

internal class GetMemeUseCaseImpl @Inject constructor(
    private val  memeRepository: MemeRepository
): GetMemeUseCase{
    override suspend fun invoke(memeId: String): Meme {
        return memeRepository.getMeme(memeId)
    }
}
