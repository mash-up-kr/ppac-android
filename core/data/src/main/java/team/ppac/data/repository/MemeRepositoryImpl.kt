package team.ppac.data.repository

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

    override suspend fun getRecommendMemes(): List<Meme> {
        return memeDataSource.getRecommendMemes()
            .map { it.toMeme() }
    }

    override suspend fun saveMeme(memeId: String): Boolean {
        return memeDataSource.saveMeme(memeId)
    }

    override suspend fun deleteSavedMeme(memeId: String): Boolean {
        return memeDataSource.deleteSavedMeme(memeId)
    }

}