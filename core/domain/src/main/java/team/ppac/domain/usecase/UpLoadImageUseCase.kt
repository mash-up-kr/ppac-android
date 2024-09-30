package team.ppac.domain.usecase

interface UpLoadImageUseCase {
    suspend operator fun invoke(uri: String)
}

internal class UpLoadImageUseCaseImpl : UpLoadImageUseCase {
    override suspend fun invoke(uri: String) {

    }
}