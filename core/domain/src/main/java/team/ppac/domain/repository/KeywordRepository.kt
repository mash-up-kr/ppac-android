package team.ppac.domain.repository

import team.ppac.domain.model.RecommendKeyword

interface KeywordRepository {

    suspend fun getRecommendKeywords(): List<RecommendKeyword>
}