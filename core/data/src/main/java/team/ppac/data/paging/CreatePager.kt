package team.ppac.data.paging

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingSource

fun <T : Any> createPager(
    pageSize: Int = ITEMS_PER_PAGE,
    pagingSourceFactory: () -> PagingSource<Int, T>,
): Pager<Int, T> = Pager(
    config = PagingConfig(
        pageSize = pageSize,
        enablePlaceholders = true,
    ),
    pagingSourceFactory = pagingSourceFactory
)