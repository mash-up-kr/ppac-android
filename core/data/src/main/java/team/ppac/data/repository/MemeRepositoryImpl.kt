package team.ppac.data.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import team.ppac.data.mapper.toMeme
import team.ppac.data.paging.ITEMS_PER_PAGE
import team.ppac.data.paging.createPager
import team.ppac.domain.model.Meme
import team.ppac.domain.model.MemeWatchType
import team.ppac.domain.model.MemeWithPagination
import team.ppac.domain.repository.MemeRepository
import team.ppac.domain.repository.SavedMemeEvent
import team.ppac.remote.datasource.MemeDataSource
import javax.inject.Inject

internal class MemeRepositoryImpl @Inject constructor(
    private val memeDataSource: MemeDataSource,
) : MemeRepository {
    override suspend fun getMeme(memeId: String): Meme {
        return memeDataSource.getMemeById(memeId).toMeme()
    }

    override suspend fun getRecommendMemes(): List<Meme> {
        return memeDataSource.getRecommendMemes()
            .map { it.toMeme() }
    }

    override suspend fun saveMeme(memeId: String): Boolean {
        return memeDataSource.saveMeme(memeId)
    }

    override suspend fun deleteSavedMeme(memeId: String): Boolean {
        return memeDataSource.deleteSavedMeme(memeId)
    }

    override suspend fun getSearchMemes(
        keyword: String,
        getCurrentPage: (Int) -> Unit,
    ): MemeWithPagination {
        val totalMemeCount = memeDataSource.getSearchMemes(
            keyword = keyword,
            page = 1,
            size = ITEMS_PER_PAGE
        ).pagination.total

        return MemeWithPagination(
            totalMemeCount = totalMemeCount,
            memes = createPager(
                executor = { page ->
                    memeDataSource.getSearchMemes(
                        keyword = keyword,
                        page = page,
                        size = ITEMS_PER_PAGE,
                    ).memeList.map { it.toMeme() }
                },
                getCurrentPage = getCurrentPage
            ).flow
        )
    }

    override suspend fun reactMeme(memeId: String): Boolean {
        return memeDataSource.reactMeme(memeId)
    }

    override suspend fun watchMeme(memeId: String, watchType: MemeWatchType): Boolean {
        return memeDataSource.watchMeme(memeId, watchType.name.lowercase())
    }

    private val _savedMemeEventFlow = MutableSharedFlow<SavedMemeEvent>()

    override val savedMemeEventFlow: Flow<SavedMemeEvent>
        get() = _savedMemeEventFlow

    override suspend fun uploadMeme(
        keywordIds: List<String>,
        memeImageUri: String,
        memeTitle: String,
        memeSource: String
    ): Boolean {
        return memeDataSource.uploadMeme(
            keywordIds = keywordIds,
            memeTitle = memeTitle,
            memeImageUri = memeImageUri,
            memeSource = memeSource
        )
    }

    override suspend fun emitRefreshEvent() {
        _savedMemeEventFlow.emit(SavedMemeEvent.Refresh)
    }
}