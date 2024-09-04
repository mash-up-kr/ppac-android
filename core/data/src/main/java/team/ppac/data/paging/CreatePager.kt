package team.ppac.data.paging

import androidx.paging.Pager
import androidx.paging.PagingConfig

fun <T : Any> createPager(
    pageSize: Int = ITEMS_PER_PAGE,
    getCurrentPage: (Int) -> Unit = {},
    executor: suspend (Int) -> List<T>,
): Pager<Int, T> = Pager(
    config = PagingConfig(
        pageSize = pageSize,
        enablePlaceholders = true,
    ),
    pagingSourceFactory = { FarmemePagingSource(executor, getCurrentPage) }
)