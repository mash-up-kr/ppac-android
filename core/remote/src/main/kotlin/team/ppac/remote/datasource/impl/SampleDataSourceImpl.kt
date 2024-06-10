package team.ppac.remote.datasource.impl

import team.ppac.remote.api.SampleApi
import team.ppac.remote.datasource.SampleDataSource
import team.ppac.remote.model.sample.SampleResponse
import javax.inject.Inject

internal class SampleDataSourceImpl @Inject constructor(
    private val sampleApi: SampleApi,
) : SampleDataSource {
    override suspend fun getImages(): List<SampleResponse> {
        return sampleApi.getImages()
    }
}