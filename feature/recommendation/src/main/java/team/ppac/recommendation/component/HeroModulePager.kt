@file:OptIn(ExperimentalFoundationApi::class)

package team.ppac.recommendation.component

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.util.lerp
import coil.compose.AsyncImage
import kotlinx.collections.immutable.ImmutableList
import team.ppac.designsystem.foundation.FarmemeRadius
import team.ppac.domain.model.Meme
import kotlin.math.absoluteValue

@Composable
fun HeroModulePager(
    images: ImmutableList<Meme>,
    pagerState: PagerState,
) {
    HorizontalPager(
        state = pagerState,
        contentPadding = PaddingValues(horizontal = 60.dp),
        pageSpacing = 12.dp,
    ) { page ->
        Box(
            modifier = Modifier
                .graphicsLayer {
                    val pageOffset =
                        ((pagerState.currentPage - page) + pagerState.currentPageOffsetFraction)
                    alpha = lerp(
                        start = 1f,
                        stop = 0.6f,
                        fraction = pageOffset.absoluteValue.coerceIn(0f, 1f),
                    )
                    scaleY = lerp(
                        start = 1f,
                        stop = 0.8f,
                        fraction = pageOffset.absoluteValue.coerceIn(0f, 1f),
                    )
                    clip = true
                    shape = FarmemeRadius.Radius40.shape
                }
                .height(310.dp))
        {
            AsyncImage(
                modifier = Modifier.fillMaxSize(),
                model = images[page].imageUrl,
                contentScale = ContentScale.Crop,
                contentDescription = "",
            )
        }
    }
}
