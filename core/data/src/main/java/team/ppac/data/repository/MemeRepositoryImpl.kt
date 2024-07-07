package team.ppac.data.repository

import team.ppac.data.mapper.toMeme
import team.ppac.domain.model.Meme
import team.ppac.domain.repository.MemeRepository
import team.ppac.remote.datasource.MemeDataSource
import javax.inject.Inject

class MemeRepositoryImpl @Inject constructor(
    private val memeDataSource: MemeDataSource,
) : MemeRepository{
    override suspend fun getMemeById(memeId: String): Meme {
       return memeDataSource.getMemeById(memeId).toMeme()
    }
}