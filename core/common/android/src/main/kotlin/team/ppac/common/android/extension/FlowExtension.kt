package team.ppac.common.android.extension

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.paging.LoadStates
import androidx.paging.PagingData
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import kotlinx.coroutines.flow.Flow

@Composable
fun <T : Any> Flow<PagingData<T>>.collectPagingItemsWithHandleState(
    handleLoadStates: (LoadStates) -> Unit,
): LazyPagingItems<T> {
    val lazyPagingItem = collectAsLazyPagingItems()
    val pagingLoadStates = lazyPagingItem.loadState.mediator ?: lazyPagingItem.loadState.source

    LaunchedEffect(pagingLoadStates) {
        handleLoadStates(pagingLoadStates)
    }

    return lazyPagingItem
}