package team.ppac.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState

internal const val ITEMS_PER_PAGE = 10
internal const val STARTING_PAGE = 1

internal class FarmemePagingSource<T : Any>(
    private val executor: suspend (Int) -> List<T>,
) : PagingSource<Int, T>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, T> {
        val currentPage = params.key ?: STARTING_PAGE

        return try {
            val response = executor(currentPage)

            LoadResult.Page(
                data = response,
                prevKey = if (currentPage == STARTING_PAGE) null else currentPage - 1,
                nextKey = if (response.isEmpty() || response.size < ITEMS_PER_PAGE) null else currentPage + 1,
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, T>): Int {
        return STARTING_PAGE
    }
}