package team.ppac.domain.usecase

import team.ppac.domain.model.RecommendKeyword
import team.ppac.domain.repository.KeywordRepository
import javax.inject.Inject

interface GetRecommendKeywordsUseCase {
    suspend operator fun invoke(): List<RecommendKeyword>
}

class GetRecommendKeywordsUseCaseImpl @Inject constructor(
    private val repository: KeywordRepository
) : GetRecommendKeywordsUseCase {

    override suspend fun invoke(): List<RecommendKeyword> = repository.getRecommendKeywords()
}