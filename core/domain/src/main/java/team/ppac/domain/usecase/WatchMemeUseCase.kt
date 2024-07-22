package team.ppac.domain.usecase

import team.ppac.domain.model.MemeWatchType
import team.ppac.domain.repository.MemeRepository
import javax.inject.Inject

interface WatchMemeUseCase {
    suspend operator fun invoke(
        memeId: String,
        watchType: MemeWatchType,
    ): Boolean
}

internal class WatchMemeUseCaseImpl @Inject constructor(
    private val memeRepository: MemeRepository,
) : WatchMemeUseCase {
    override suspend fun invoke(
        memeId: String,
        watchType: MemeWatchType,
    ): Boolean = memeRepository.watchMeme(memeId, watchType)
}