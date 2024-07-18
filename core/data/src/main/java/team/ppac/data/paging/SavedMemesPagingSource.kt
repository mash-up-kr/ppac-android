package team.ppac.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import team.ppac.data.repository.UserRepositoryImpl.Companion.SAVED_MEME_PAGING_PAGE
import team.ppac.domain.model.Meme

private const val MEME_STARTING_PAGE_INDEX = 1

internal class SavedMemesPagingSource(
    private val getSavedMemes: suspend (Int) -> List<Meme>,
) : PagingSource<Int, Meme>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Meme> {
        return try {
            val currentPage = params.key ?: MEME_STARTING_PAGE_INDEX
            val response = getSavedMemes(currentPage)

            LoadResult.Page(
                data = response,
                prevKey = if (currentPage == MEME_STARTING_PAGE_INDEX) null else currentPage - 1,
                nextKey = if (response.isEmpty() || response.size < SAVED_MEME_PAGING_PAGE) null else currentPage + 1,
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Meme>): Int {
        return ((state.anchorPosition ?: 0) - state.config.initialLoadSize / 2)
            .coerceAtLeast(0)
    }
}