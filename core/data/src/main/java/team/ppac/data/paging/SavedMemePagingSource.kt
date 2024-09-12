package team.ppac.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import team.ppac.domain.model.Meme
import team.ppac.domain.model.paging.PageData

internal class SavedMemePagingSource(
    private val getCurrentPage: (Int) -> Unit,
    private val executor: suspend (Int) -> PageData<List<Meme>>,
) : PagingSource<Int, Meme>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Meme> {
        val currentPage = params.key ?: STARTING_PAGE

        return try {
            val response = executor(currentPage)
            getCurrentPage(currentPage)

            LoadResult.Page(
                data = response.data,
                prevKey = if (currentPage == STARTING_PAGE) null else currentPage - 1,
                nextKey = if (response.data.isEmpty() || response.data.size < ITEMS_PER_PAGE) null else currentPage + 1,
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Meme>): Int? {
        return STARTING_PAGE
    }
}