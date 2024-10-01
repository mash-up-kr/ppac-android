package team.ppac.domain.usecase

import team.ppac.domain.repository.MemeRepository
import javax.inject.Inject

interface UploadMemeUseCase {
    suspend operator fun invoke(
        keywordIds: List<String>,
        memeImageUri: String,
        memeTitle: String,
        memeSource: String
    ): Boolean
}

internal class UploadMemeUseCaseImpl @Inject constructor(
    private val memeRepository: MemeRepository
) : UploadMemeUseCase {
    override suspend fun invoke(
        keywordIds: List<String>,
        memeImageUri: String,
        memeTitle: String,
        memeSource: String
    ): Boolean {
        return memeRepository.uploadMeme(
            keywordIds = keywordIds,
            memeTitle = memeTitle,
            memeImageUri = memeImageUri,
            memeSource = memeSource
        )
    }
}