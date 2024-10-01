package team.ppac.domain.usecase

import team.ppac.domain.model.MemeWithPagination
import team.ppac.domain.repository.MemeRepository
import javax.inject.Inject

interface SearchMemeUseCase {
    suspend operator fun invoke(query: String): MemeWithPagination
}

internal class SearchMemeUseCaseImpl @Inject constructor(
    private val repository: MemeRepository,
) : SearchMemeUseCase {

    override suspend operator fun invoke(query: String): MemeWithPagination {
        return repository.searchMeme(query = query)
    }
}