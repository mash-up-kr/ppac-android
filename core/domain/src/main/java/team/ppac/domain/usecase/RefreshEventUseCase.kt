package team.ppac.domain.usecase

import kotlinx.coroutines.flow.Flow
import team.ppac.domain.repository.MemeRepository
import team.ppac.domain.repository.RefreshSavedMemeEvent
import javax.inject.Inject

interface RefreshEventUseCase {
    suspend operator fun invoke(): Flow<RefreshSavedMemeEvent>
}

internal class RefreshEventUseCaseImpl @Inject constructor(
    private val memeRepository: MemeRepository,
) : RefreshEventUseCase {
    override suspend fun invoke(): Flow<RefreshSavedMemeEvent> {
        return memeRepository.refreshSavedMemeEventFlow
    }
}