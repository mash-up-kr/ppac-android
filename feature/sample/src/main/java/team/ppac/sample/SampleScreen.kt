package team.ppac.sample

import android.graphics.Bitmap
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.util.lerp
import androidx.core.graphics.drawable.toBitmap
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import team.ppac.common.android.util.copyImageToClipBoard
import team.ppac.designsystem.FarmemeTheme
import team.ppac.domain.model.SampleImageModel
import team.ppac.sample.mvi.SampleIntent
import kotlin.math.absoluteValue

@Composable
fun SampleScreen(viewModel: SampleViewModel) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        val context = LocalContext.current

        Button(
            onClick = {
                viewModel.intent(SampleIntent.ClickGetImagesButton)
            },
        ) {
            Text("이미지 로드")
        }

        if (state.isLoading) {
            Text(
                style = FarmemeTheme.typography.body.large.bold,
                fontSize = 30.sp,
                text = "로딩중",
            )
        }


        HeroHorizontalPager(images = state.images)
        LazyColumn {
            items(items = state.images) {
                var bitmap = remember<Bitmap?> { null }

                AsyncImage(
                    modifier = Modifier.clickable {
                        bitmap?.let {
                            context.copyImageToClipBoard(it)
                        }
                    },
                    model = it.imageUrl,
                    contentDescription = "",
                    onSuccess = {
                        bitmap = it.result.drawable.toBitmap()
                    }
                )
            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HeroHorizontalPager(
    images: List<SampleImageModel>,
) {
    val pagerState = rememberPagerState {
        images.size
    }
    HorizontalPager(
        modifier = Modifier.fillMaxSize(),
        state = pagerState,
        contentPadding = PaddingValues(horizontal = 60.dp),
        pageSpacing = 12.dp,
    ) { page ->
        AsyncImage(
            modifier = Modifier
                .size(300.dp, 300.dp)
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
                },
            model = images[page].imageUrl,
            contentDescription = "",
        )
    }
}
