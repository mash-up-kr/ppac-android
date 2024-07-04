package team.ppac.data.repository

import team.ppac.domain.model.Meme
import team.ppac.domain.repository.RecommendationRepository
import team.ppac.remote.datasource.SampleDataSource
import javax.inject.Inject

internal class RecommendationRepositoryImpl @Inject constructor(
    private val sampleDataSource: SampleDataSource,
) : RecommendationRepository {

    override suspend fun getLastSeenMemeCount(): Int {
        return (0 until 5).random()
    }

    override suspend fun getThisWeekMemes(): List<Meme> {
        return sampleDataSource.getImages().map {
            Meme(
                id = it.id.toLong(),
                url = it.url,
            )
        }
    }
}