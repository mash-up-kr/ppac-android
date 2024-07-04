package team.ppac.domain.usecase

import team.ppac.domain.repository.RecommendationRepository
import javax.inject.Inject

interface GetLastSeenMemeCountUseCase {
    operator fun invoke(): Int
}

internal class GetLastSeenMemeCountUseCaseImpl @Inject constructor(
    private val recommendationRepository: RecommendationRepository,
) : GetLastSeenMemeCountUseCase {
    override fun invoke(): Int {
        return recommendationRepository.getLastSeenMemeCount()
    }
}