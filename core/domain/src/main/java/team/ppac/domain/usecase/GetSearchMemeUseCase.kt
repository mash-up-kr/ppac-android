package team.ppac.domain.usecase

import team.ppac.domain.model.MemeWithPagination
import team.ppac.domain.repository.MemeRepository
import javax.inject.Inject

interface GetSearchMemeUseCase {
    suspend operator fun invoke(keyword: String): MemeWithPagination
}

internal class GetSearchMemeUseCaseImpl @Inject constructor(
    private val memeRepository: MemeRepository,
) : GetSearchMemeUseCase {
    override suspend fun invoke(keyword: String): MemeWithPagination {
        return memeRepository.getSearchMemes(keyword)
    }
}