package team.ppac.domain.repository

import team.ppac.domain.model.SampleImageModel

interface SampleRepository {
    suspend fun getImages(): List<SampleImageModel>
}