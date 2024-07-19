package team.ppac.common.android.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.PullRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun FarmemePullRefreshIndicator(
    modifier: Modifier = Modifier,
    isLoading: Boolean,
    pullRefreshState: PullRefreshState,
) {
    Box(
        modifier = modifier.fillMaxWidth(),
        contentAlignment = Alignment.Center,
    ) {
        PullRefreshIndicator(
            refreshing = isLoading,
            state = pullRefreshState,
        )
    }
}