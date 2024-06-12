package team.ppac.domain.usecase

import team.ppac.domain.model.SampleImageModel
import team.ppac.domain.repository.SampleRepository
import javax.inject.Inject

interface SampleUseCase {
    suspend fun getImages(): List<SampleImageModel>
}

internal class SampleUseCaseImpl @Inject constructor(
    private val sampleRepository: SampleRepository
) : SampleUseCase {
    override suspend fun getImages(): List<SampleImageModel> {
        return sampleRepository.getImages()
    }
}