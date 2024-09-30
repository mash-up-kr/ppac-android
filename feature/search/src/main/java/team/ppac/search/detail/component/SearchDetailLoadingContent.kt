package team.ppac.search.detail.component

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
import team.ppac.common.android.util.showSkeleton
import team.ppac.designsystem.foundation.FarmemeRadius

@Composable
internal fun SearchDetailLoadingContent(
    modifier: Modifier = Modifier,
    isLoading: Boolean,
) {
    Column(
        modifier = modifier
    ) {
        Spacer(modifier = Modifier.size(20.dp))
        Box(
            modifier = Modifier
                .width(100.dp)
                .height(18.dp)
                .padding(start = 20.dp)
                .clip(FarmemeRadius.Radius4.shape)
                .showSkeleton(isLoading = isLoading)
        )
        Spacer(modifier = Modifier.size(20.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            repeat(2) {
                Column(
                    modifier = Modifier.weight(1f)
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(210.dp)
                            .clip(RoundedCornerShape(16.dp))
                            .showSkeleton(isLoading = isLoading)
                    )
                    Spacer(modifier = Modifier.size(10.dp))
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(18.dp)
                            .clip(FarmemeRadius.Radius4.shape)
                            .showSkeleton(isLoading = isLoading)
                    )
                    Spacer(modifier = Modifier.size(10.dp))
                    Box(
                        modifier = Modifier
                            .width(80.dp)
                            .height(18.dp)
                            .clip(FarmemeRadius.Radius4.shape)
                            .showSkeleton(isLoading = isLoading)
                    )
                }
            }
        }
    }
}

@Preview
@Composable
private fun SearchDetailLoadingContentPreview() {
    Box(modifier = Modifier.background(Color.White)) {
        SearchDetailLoadingContent(
            modifier = Modifier.fillMaxSize(),
            isLoading = true
        )
    }
}