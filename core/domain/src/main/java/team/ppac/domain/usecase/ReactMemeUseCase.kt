package team.ppac.domain.usecase

import team.ppac.domain.repository.MemeRepository
import javax.inject.Inject

interface ReactMemeUseCase {
    suspend operator fun invoke(memeId: String): Boolean
}

internal class ReactMemeUseCaseImpl @Inject constructor(
    private val memeRepository: MemeRepository,
) : ReactMemeUseCase {
    override suspend fun invoke(memeId: String): Boolean {
        return memeRepository.reactMeme(memeId)
    }
}