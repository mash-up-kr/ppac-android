package team.ppac.remote.datasource.impl

import team.ppac.remote.api.KeywordApi
import team.ppac.remote.datasource.KeywordDataSource
import team.ppac.remote.model.response.keyword.RecommendKeywordResponse
import team.ppac.remote.model.response.keyword.TopKeywordResponse
import javax.inject.Inject

internal class KeywordDataSourceImpl @Inject constructor(
    private val keywordApi: KeywordApi,
) : KeywordDataSource {
    override suspend fun getRecommendKeywords(): List<RecommendKeywordResponse> =
        keywordApi.getRecommendKeywords()

    override suspend fun getTopKeywords(): List<TopKeywordResponse> = keywordApi.getTopKeywords()
}
