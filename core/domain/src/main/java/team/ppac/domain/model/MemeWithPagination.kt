package team.ppac.domain.model

import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow

data class MemeWithPagination(
    val totalMemeCount: Int,
    val memes: Flow<PagingData<Meme>>,
)
