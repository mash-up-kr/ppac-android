package team.ppac.data.mapper

import team.ppac.domain.model.SampleImageModel
import team.ppac.remote.model.sample.SampleResponse

internal fun List<SampleResponse>.toDomain(): List<SampleImageModel> {
    return this.map { sampleResponse ->
        SampleImageModel(sampleResponse.download_url, sampleResponse.author)
    }
}