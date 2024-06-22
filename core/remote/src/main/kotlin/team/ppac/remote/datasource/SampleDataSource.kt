package team.ppac.remote.datasource

import team.ppac.remote.model.response.sample.SampleResponse

interface SampleDataSource {
    suspend fun getImages(): List<SampleResponse>
}