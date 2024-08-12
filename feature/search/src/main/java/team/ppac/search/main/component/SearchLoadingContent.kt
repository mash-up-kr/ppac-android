package team.ppac.search.main.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import team.ppac.common.android.util.SkeletonViewType
import team.ppac.common.android.util.showSkeleton
import team.ppac.designsystem.foundation.FarmemeRadius
import team.ppac.search.main.mvi.SearchUiState

@Composable
internal fun SearchLoadingContent(
    modifier: Modifier = Modifier,
    uiState: SearchUiState,
) {
    Column(
        modifier = modifier
    ) {
        Spacer(modifier = Modifier.size(16.dp))
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(44.dp)
                .padding(horizontal = 20.dp)
                .clip(FarmemeRadius.Radius10.shape)
                .showSkeleton(isLoading = uiState.isLoading)
        )
        Spacer(modifier = Modifier.size(34.dp))
        Box(
            modifier = Modifier
                .width(200.dp)
                .height(20.dp)
                .padding(horizontal = 20.dp)
                .clip(FarmemeRadius.Radius4.shape)
                .showSkeleton(isLoading = uiState.isLoading)
        )
        Spacer(modifier = Modifier.size(18.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 20.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            repeat(4) {
                Box(
                    modifier = Modifier
                        .width(90.dp)
                        .height(90.dp)
                        .clip(FarmemeRadius.Radius12.shape)
                        .showSkeleton(isLoading = uiState.isLoading)
                )
            }
        }
        Spacer(modifier = Modifier.size(58.dp))
        Box(
            modifier = Modifier
                .width(200.dp)
                .height(20.dp)
                .padding(horizontal = 20.dp)
                .clip(FarmemeRadius.Radius4.shape)
                .showSkeleton(isLoading = uiState.isLoading)
        )
        Spacer(modifier = Modifier.size(22.dp))
        Box(
            modifier = Modifier
                .width(60.dp)
                .height(16.dp)
                .padding(start = 20.dp)
                .clip(FarmemeRadius.Radius4.shape)
                .showSkeleton(isLoading = uiState.isLoading)
        )
        Spacer(modifier = Modifier.size(16.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 20.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            repeat(3) {
                Box(
                    modifier = Modifier
                        .width(60.dp)
                        .height(36.dp)
                        .clip(RoundedCornerShape(18.dp))
                        .showSkeleton(isLoading = uiState.isLoading)
                )
            }
        }
    }
}

@Preview
@Composable
private fun SearchLoadingContentPreview() {
    Box(modifier = Modifier.background(Color.White)) {
        SearchLoadingContent(
            modifier = Modifier.fillMaxSize(),
            uiState = SearchUiState.INITIAL_STATE
        )
    }
}