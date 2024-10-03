package team.ppac.domain.usecase

import team.ppac.domain.model.ReactionMeme
import team.ppac.domain.repository.MemeRepository
import javax.inject.Inject

interface ReactMemeUseCase {
    suspend operator fun invoke(memeId: String, count: Int): ReactionMeme
}

internal class ReactMemeUseCaseImpl @Inject constructor(
    private val memeRepository: MemeRepository,
) : ReactMemeUseCase {
    override suspend fun invoke(memeId: String, count: Int): ReactionMeme {
        return memeRepository.reactMeme(memeId, count)
    }
}