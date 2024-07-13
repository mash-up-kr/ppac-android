package team.ppac.domain.usecase

import team.ppac.domain.model.Keyword
import team.ppac.domain.repository.KeywordRepository
import javax.inject.Inject

interface GetTopKeywordsUseCase {
    suspend operator fun invoke(): List<Keyword>
}

internal class GetTopKeywordsUseCaseImpl @Inject constructor(
    private val repository: KeywordRepository,
) : GetTopKeywordsUseCase {
    override suspend fun invoke() = repository.getTopKeywords()
}