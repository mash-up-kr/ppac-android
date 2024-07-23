package team.ppac.domain.usecase

import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import team.ppac.domain.model.Meme
import team.ppac.domain.repository.MemeRepository
import javax.inject.Inject

interface GetSearchMemeUseCase {
    operator fun invoke(keyword: String): Flow<PagingData<Meme>>
}

internal class GetSearchMemeUseCaseImpl @Inject constructor(
    private val memeRepository: MemeRepository,
) : GetSearchMemeUseCase {
    override fun invoke(keyword: String): Flow<PagingData<Meme>> {
        return memeRepository.getSearchMemes(keyword)
    }
}