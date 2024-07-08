package team.ppac.domain.usecase

import kotlinx.collections.immutable.ImmutableList
import team.ppac.domain.model.Meme
import team.ppac.domain.repository.MemeRepository
import javax.inject.Inject

interface GetThisWeekRecommendMemesUseCase {
    suspend operator fun invoke(): ImmutableList<Meme>
}

internal class GetThisWeekRecommendMemesUseCaseImpl @Inject constructor(
    private val memeRepository: MemeRepository,
) : GetThisWeekRecommendMemesUseCase {
    override suspend fun invoke(): ImmutableList<Meme> {
        return memeRepository.getRecommendMemes()
    }
}