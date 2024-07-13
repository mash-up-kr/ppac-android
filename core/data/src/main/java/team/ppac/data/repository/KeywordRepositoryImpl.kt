package team.ppac.data.repository

import team.ppac.data.mapper.toRecommendKeyword
import team.ppac.domain.model.RecommendKeyword
import team.ppac.domain.repository.KeywordRepository
import team.ppac.remote.datasource.KeywordDataSource
import javax.inject.Inject

class KeywordRepositoryImpl @Inject constructor(
    private val remoteDataSource: KeywordDataSource,
) : KeywordRepository {

    override suspend fun getRecommendKeywords(): List<RecommendKeyword> =
        remoteDataSource.getRecommendKeywords().map { it.toRecommendKeyword() }
}
