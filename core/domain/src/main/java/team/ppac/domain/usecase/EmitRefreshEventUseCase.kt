package team.ppac.domain.usecase

import team.ppac.domain.repository.MemeRepository
import javax.inject.Inject

interface EmitRefreshEventUseCase {
    suspend operator fun invoke()
}

internal class EmitRefreshEventUseCaseImpl @Inject constructor(
    private val memeRepository: MemeRepository,
) : EmitRefreshEventUseCase {
    override suspend fun invoke() {
        return memeRepository.emitRefreshEvent()
    }
}