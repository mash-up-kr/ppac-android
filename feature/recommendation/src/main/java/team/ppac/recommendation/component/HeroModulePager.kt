@file:OptIn(ExperimentalFoundationApi::class)

package team.ppac.recommendation.component

import android.graphics.Bitmap
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.graphics.BlurEffect
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.painter.ColorPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.util.lerp
import androidx.core.graphics.drawable.toBitmap
import coil.compose.AsyncImage
import kotlinx.collections.immutable.ImmutableList
import team.ppac.designsystem.FarmemeTheme
import team.ppac.designsystem.foundation.FarmemeRadius
import team.ppac.domain.model.Meme
import kotlin.math.absoluteValue


//비트맵 가져오는 방법 생각하기
@Composable
fun HeroModulePager(
    modifier: Modifier = Modifier,
    memes: ImmutableList<Meme>,
    pagerState: PagerState,
    onMovePage: (page: Int, Meme) -> Unit,
    onLoadMeme: (page: Int, bitmap: Bitmap) -> Unit,
) {
    LaunchedEffect(pagerState) {
        snapshotFlow { pagerState.currentPage }
            .collect {
                onMovePage(it, memes[it])
            }
    }
    HorizontalPager(
        modifier = modifier,
        state = pagerState,
        contentPadding = PaddingValues(horizontal = 60.dp),
        pageSpacing = 12.dp,
        beyondBoundsPageCount = 3,
    ) { page ->
        val pageOffset =
            ((pagerState.currentPage - page) + pagerState.currentPageOffsetFraction)
        val dimmed = FarmemeTheme.backgroundColor.dimmer
        Box(
            modifier = Modifier
                .graphicsLayer {
                    alpha = lerp(
                        start = 1f,
                        stop = 0.6f,
                        fraction = pageOffset.absoluteValue.coerceIn(0f, 1f),
                    )
                    scaleY = lerp(
                        start = 1f,
                        stop = 0.9f,
                        fraction = pageOffset.absoluteValue.coerceIn(0f, 1f),
                    )
                    clip = true
                    shape = FarmemeRadius.Radius40.shape
                    renderEffect = if (pageOffset != 0f) {
                        BlurEffect(
                            lerp(
                                start = 1f,
                                stop = 27f, // 270 / 10
                                fraction = pageOffset.absoluteValue.coerceIn(0f, 1f),
                            ), lerp(
                                start = 1f,
                                stop = 31f, // 310 / 10
                                fraction = pageOffset.absoluteValue.coerceIn(0f, 1f),
                            )
                        )
                    } else {
                        null
                    }
                }
                .run {
                    if (pageOffset != 0f) {
                        drawWithContent {
                            drawContent()
                            drawRect(
                                dimmed.copy(
                                    alpha = lerp(
                                        start = 0f,
                                        stop = 0.6f,
                                        fraction = pageOffset.absoluteValue.coerceIn(0f, 1f),
                                    )
                                )
                            )
                        }
                    } else {
                        this
                    }
                }
                .border(
                    border = BorderStroke(
                        width = 2.dp,
                        color = FarmemeTheme.borderColor.primary,
                    ),
                    shape = FarmemeRadius.Radius40.shape,
                )
                .height(310.dp))
        {
            AsyncImage(
                modifier = Modifier
                    .fillMaxSize()
                    .background(FarmemeTheme.backgroundColor.black),
                model = memes[page].imageUrl,
                contentScale = ContentScale.Fit,
                error = ColorPainter(FarmemeTheme.skeletonColor.primary),
                placeholder = ColorPainter(FarmemeTheme.skeletonColor.primary),
                contentDescription = "",
                onSuccess = {
                    onLoadMeme(page, it.result.drawable.toBitmap())
                }
            )
        }
    }
}
