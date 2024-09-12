package team.ppac.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import team.ppac.domain.model.paging.PageData
import team.ppac.domain.model.paging.PagingInfo

internal const val ITEMS_PER_PAGE = 10
internal const val STARTING_PAGE = 1

internal class FarmemePagingSource<T : Any>(
    private val getCurrentPage: (Int) -> Unit,
    private val executor: suspend (Int) -> PageData<List<T>>,
) : PagingSource<Int, T>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, T> {
        val currentPage = params.key ?: STARTING_PAGE

        return try {
            val response = executor(currentPage)
            val isLastPage = hasReachedEnd(currentPage, response.pagingInfo)

            getCurrentPage(currentPage)

            LoadResult.Page(
                data = response.data,
                prevKey = if (currentPage == STARTING_PAGE) null else currentPage - 1,
                nextKey = if (isLastPage) null else currentPage + 1,
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, T>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    private fun hasReachedEnd(currentPage: Int, pagingInfo: PagingInfo): Boolean {
        return when {
            pagingInfo.totalPage == 0 -> true
            pagingInfo.totalPagingCount == 0 -> true
            currentPage < pagingInfo.totalPage -> false
            else -> true
        }
    }
}