package team.ppac.data.repository

import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.toImmutableList
import team.ppac.data.mapper.toMeme
import team.ppac.domain.model.Meme
import team.ppac.domain.repository.MemeRepository
import team.ppac.remote.datasource.MemeDataSource
import javax.inject.Inject

class MemeRepositoryImpl @Inject constructor(
    private val memeDataSource: MemeDataSource,
) : MemeRepository {
    override suspend fun getMeme(memeId: String): Meme {
        return memeDataSource.getMemeById(memeId).toMeme()
    }

    override suspend fun getRecommendMemes(): ImmutableList<Meme> {
        return memeDataSource.getRecommendMemes()
            .map { it.toMeme() }
            .toImmutableList()
    }
}