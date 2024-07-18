package team.ppac.remote.datasource.impl

import team.ppac.remote.api.MemeApi
import team.ppac.remote.datasource.MemeDataSource
import team.ppac.remote.model.response.meme.MemeResponse
import javax.inject.Inject

internal class MemeDataSourceImpl @Inject constructor(
    private val memeApi: MemeApi,
) : MemeDataSource {
    override suspend fun getMemeById(memeId: String): MemeResponse {
        return memeApi.getMemeById(memeId = memeId)
    }

    override suspend fun getRecommendMemes(): List<MemeResponse> {
        return memeApi.getRecommendMemes()
    }

    override suspend fun saveMeme(memeId: String): Boolean {
        return memeApi.saveMeme(memeId)
    }

    override suspend fun deleteSavedMeme(memeId: String): Boolean {
        return memeApi.deleteSavedMeme(memeId)
    }
}