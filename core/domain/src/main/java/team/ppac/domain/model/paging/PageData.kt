package team.ppac.domain.model.paging

data class PageData<T>(
    val data: T,
    val pagingInfo: PagingInfo,
)
