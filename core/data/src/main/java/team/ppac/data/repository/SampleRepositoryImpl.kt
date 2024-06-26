package team.ppac.data.repository

import team.ppac.data.mapper.toSampleImageModel
import team.ppac.domain.model.SampleImageModel
import team.ppac.domain.repository.SampleRepository
import team.ppac.remote.datasource.SampleDataSource
import javax.inject.Inject

internal class SampleRepositoryImpl @Inject constructor(
    private val sampleDataSource: SampleDataSource
) : SampleRepository {
    override suspend fun getImages(): List<SampleImageModel> {
        return sampleDataSource.getImages().toSampleImageModel()
    }
}