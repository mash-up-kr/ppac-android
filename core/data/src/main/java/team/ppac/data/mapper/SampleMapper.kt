package team.ppac.data.mapper

import team.ppac.domain.model.SampleImageModel
import team.ppac.remote.model.response.sample.SampleResponse

internal fun List<SampleResponse>.toSampleImageModel(): List<SampleImageModel> {
    return this.map { sampleResponse ->
        SampleImageModel(sampleResponse.download_url, sampleResponse.author)
    }
}