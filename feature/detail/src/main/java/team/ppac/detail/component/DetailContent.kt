package team.ppac.detail.component

import android.graphics.Bitmap
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.boundsInWindow
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.graphics.drawable.toBitmap
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.rememberLottieAnimatable
import com.airbnb.lottie.compose.rememberLottieComposition
import kotlinx.coroutines.launch
import kotlinx.collections.immutable.ImmutableList
import team.ppac.common.kotlin.extension.truncateDisplayedString
import team.ppac.common.kotlin.extension.truncateDisplayedList
import team.ppac.designsystem.FarmemeTheme
import team.ppac.designsystem.R
import team.ppac.designsystem.foundation.FarmemeIcon
import team.ppac.designsystem.foundation.FarmemeRadius
import team.ppac.designsystem.util.extension.singleClickable
import team.ppac.detail.model.DetailMemeUiModel
import team.ppac.detail.mvi.DetailUiState

@Composable
internal fun DetailContent(
    modifier: Modifier = Modifier,
    uiModel: DetailMemeUiModel,
    saveBitmap: (Bitmap) -> Unit,
    onClickFunnyButton: () -> Unit,
    onReactionButtonPositioned: (Offset) -> Unit,
) {
    Box(
        modifier = modifier
            .border(
                width = 2.dp,
                color = FarmemeTheme.borderColor.primary,
                shape = FarmemeRadius.Radius20.shape,
            ),
    ) {
        Column(
            modifier = Modifier.padding(10.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            DetailImage(
                imageUrl = uiModel.imageUrl,
                saveBitmap = saveBitmap,
            )
            DetailHashTags(
                name = uiModel.name,
                sourceDescription = uiModel.sourceDescription,
                hashTags = uiModel.hashTags
            )
            DetailFunnyButton(
                reactionCount = uiModel.reactionCount,
                onClickFunnyButton = onClickFunnyButton,
                onReactionButtonPositioned = onReactionButtonPositioned
            )
            Spacer(modifier = Modifier.height(10.dp))
        }
    }
}

@Composable
private fun DetailImage(
    imageUrl: String,
    saveBitmap: (Bitmap) -> Unit
) {
    Box {
        AsyncImage(
            model = ImageRequest
                .Builder(LocalContext.current)
                .data(imageUrl)
                .crossfade(true)
                .build(),
            placeholder = painterResource(R.drawable.detail_sample),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .clip(FarmemeRadius.Radius10.shape)
                .width(330.dp)
                .height(352.dp),
            onSuccess = { saveBitmap(it.result.drawable.toBitmap()) }
        )
        Box(
            modifier = Modifier
                .matchParentSize()
                .graphicsLayer(alpha = 0.80f)
                .clip(FarmemeRadius.Radius10.shape)
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(Color.Transparent, FarmemeTheme.iconColor.secondary),
                        startY = 320f,
                        endY = 1000f
                    )
                )
        )
    }
}

@Composable
internal fun DetailHashTags(
    name: String,
    sourceDescription: String,
    hashTags: ImmutableList<String>,
) {

    Spacer(modifier = Modifier.height(25.dp))
    Text(
        text = name.truncateDisplayedString(16),
        color = FarmemeTheme.textColor.primary,
        style = FarmemeTheme.typography.heading.large.semibold,
        overflow = TextOverflow.Ellipsis
    )
    Spacer(modifier = Modifier.height(5.dp))
    DetailTags(hashTags = hashTags.truncateDisplayedList(6))
    Spacer(modifier = Modifier.height(11.dp))
    Text(
        text = "출처: $sourceDescription".truncateDisplayedString(32),
        color = FarmemeTheme.textColor.assistive,
        style = FarmemeTheme.typography.body.xSmall.medium,
        maxLines = 1,
        overflow = TextOverflow.Ellipsis
    )
    Spacer(modifier = Modifier.height(20.dp))
}

@Composable
internal fun DetailTags(hashTags: List<String>) {
    Row {
        hashTags.forEach { hashTag ->
            Text(
                text = "#$hashTag",
                color = FarmemeTheme.textColor.tertiary,
                style = FarmemeTheme.typography.body.large.medium,
            )
            Spacer(modifier = Modifier.width(6.dp))
        }
    }
}

@Composable
fun DetailFunnyButton(
    reactionCount: Int,
    onClickFunnyButton: () -> Unit,
    onReactionButtonPositioned: (Offset) -> Unit,
) {
    val lottieComposition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.lol_move_effect))
    val coroutineScope = rememberCoroutineScope()
    val lottieAnimatable = rememberLottieAnimatable()
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(46.dp)
            .clip(FarmemeRadius.Radius10.shape)
            .background(color = FarmemeTheme.skeletonColor.primary)
            .singleClickable(
                rippleColor = FarmemeTheme.skeletonColor.secondary,
                onClick = {
                    coroutineScope.launch {
                        lottieAnimatable.animate(composition = lottieComposition)
                    }
                    onClickFunnyButton()
                }
            )
            .onGloballyPositioned {
                val bound = it.boundsInWindow()
                onReactionButtonPositioned(bound.topLeft)
            },
        contentAlignment = Alignment.Center
    ) {
        if (reactionCount == 0) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                FarmemeIcon.KK()
                Spacer(modifier = Modifier.width(6.dp))
                FarmemeIcon.Funny()
            }
        }

        AnimatedVisibility(
            visible = reactionCount > 0,
            enter = fadeIn(),
            exit = fadeOut(),
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                LottieAnimation(
                    modifier = Modifier.size(
                        height = 22.dp,
                        width = 44.dp,
                    ),
                    composition = lottieComposition,
                    progress = { lottieAnimatable.progress },
                )
                Spacer(modifier = Modifier.width(6.dp))
                Text(
                    text = "+$reactionCount",
                    style = FarmemeTheme.typography.highlight.basic,
                    color = FarmemeTheme.textColor.brand
                )
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
fun PreviewDetailContent() {
    DetailContent(
        modifier = Modifier,
        uiModel = DetailUiState.INITIAL_STATE.detailMemeUiModel,
        saveBitmap = {},
        onClickFunnyButton = {},
        onReactionButtonPositioned = { _ -> },
    )
}