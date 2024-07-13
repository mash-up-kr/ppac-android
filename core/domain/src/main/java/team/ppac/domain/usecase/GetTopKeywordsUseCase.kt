package team.ppac.domain.usecase

import team.ppac.domain.repository.KeywordRepository
import javax.inject.Inject

class GetTopKeywordsUseCase @Inject constructor(
    private val repository: KeywordRepository,
) {
    suspend operator fun invoke() = repository.getTopKeywords()
}