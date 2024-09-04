package team.ppac.domain.usecase

import team.ppac.domain.model.MemeWithPagination
import team.ppac.domain.repository.MemeRepository
import javax.inject.Inject

interface GetSearchMemeUseCase {
    suspend operator fun invoke(keyword: String, getCurrentPage: (Int) -> Unit): MemeWithPagination
}

internal class GetSearchMemeUseCaseImpl @Inject constructor(
    private val memeRepository: MemeRepository,
) : GetSearchMemeUseCase {
    override suspend fun invoke(
        keyword: String,
        getCurrentPage: (Int) -> Unit
    ): MemeWithPagination {
        return memeRepository.getSearchMemes(keyword, getCurrentPage)
    }
}