package team.ppac.domain.usecase

import team.ppac.domain.model.Meme
import team.ppac.domain.repository.MemeRepository
import javax.inject.Inject

interface GetThisWeekRecommendMemesUseCase {
    suspend operator fun invoke(): List<Meme>
}

internal class GetThisWeekRecommendMemesUseCaseImpl @Inject constructor(
    private val memeRepository: MemeRepository,
) : GetThisWeekRecommendMemesUseCase {
    override suspend fun invoke(): List<Meme> {
        return memeRepository.getRecommendMemes()
    }
}