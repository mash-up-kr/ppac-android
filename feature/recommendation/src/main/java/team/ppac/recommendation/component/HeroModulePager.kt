@file:OptIn(ExperimentalFoundationApi::class)

package team.ppac.recommendation.component

import android.graphics.Bitmap
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
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
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.util.lerp
import androidx.core.graphics.drawable.toBitmap
import coil.compose.AsyncImage
import kotlinx.collections.immutable.ImmutableList
import team.ppac.designsystem.FarmemeTheme
import team.ppac.designsystem.R
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
                modifier = Modifier.fillMaxSize(),
                model = memes[page].imageUrl,
                contentScale = ContentScale.Crop,
                error = painterResource(id = R.drawable.img_sample),  // TODO(JaesungLeee) : API 연결 후 제거 필요
                placeholder = painterResource(id = R.drawable.img_sample),  // TODO(JaesungLeee) : API 연결 후 제거 필요
                contentDescription = "",
                onSuccess = {
                    onLoadMeme(page, it.result.drawable.toBitmap())
                }
            )
        }
    }
}
