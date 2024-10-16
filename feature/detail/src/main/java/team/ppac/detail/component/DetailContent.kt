package team.ppac.detail.component

import android.graphics.Bitmap
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.core.graphics.drawable.toBitmap
import coil.compose.AsyncImage
import coil.request.CachePolicy
import coil.request.ImageRequest
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.rememberLottieAnimatable
import com.airbnb.lottie.compose.rememberLottieComposition
import kotlinx.collections.immutable.ImmutableList
import kotlinx.coroutines.launch
import team.ppac.common.android.util.showSkeleton
import team.ppac.common.kotlin.extension.truncateDisplayedList
import team.ppac.common.kotlin.extension.truncateDisplayedString
import team.ppac.designsystem.FarmemeTheme
import team.ppac.designsystem.R
import team.ppac.designsystem.component.dim.FarmemeImageDim
import team.ppac.designsystem.foundation.FarmemeIcon
import team.ppac.designsystem.foundation.FarmemeRadius
import team.ppac.designsystem.util.extension.noRippleClickable
import team.ppac.designsystem.util.extension.rippleClickable
import team.ppac.detail.model.DetailMemeUiModel
import team.ppac.detail.mvi.DetailUiState
import team.ppac.detail.util.DetailScreenSize
import team.ppac.detail.util.toImageSize

@Composable
internal fun DetailContent(
    modifier: Modifier = Modifier,
    isLoading: Boolean,
    uiModel: DetailMemeUiModel,
    saveBitmap: (Bitmap) -> Unit,
    onClickFunnyButton: () -> Unit,
    onReactionButtonPositioned: (Offset) -> Unit,
    onHashTagsClick: () -> Unit,
    currentDetailScreenSize: DetailScreenSize
) {
    Box {
        DetailImage(
            imageUrl = uiModel.imageUrl,
            isLoading = isLoading,
            saveBitmap = saveBitmap,
            currentDetailScreenSize = currentDetailScreenSize
        )
        if (currentDetailScreenSize == DetailScreenSize.SMALL) {
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
    Column(
        modifier = Modifier
            .padding(horizontal = 10.dp)
            .padding(top = 20.dp, bottom = 10.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        DetailHashTags(
            name = uiModel.name,
            sourceDescription = uiModel.sourceDescription,
            hashTags = uiModel.hashTags,
            isLoading = isLoading,
            onHashTagsClick = onHashTagsClick,
            currentDetailScreenSize = currentDetailScreenSize,
        )
        DetailFunnyButton(
            modifier = Modifier.mapTextSkeletonModifierIfNeed(
                isLoading = isLoading,
                height = 46.dp,
                shape = FarmemeRadius.Radius10.shape,
            ),
            reactionCount = uiModel.reactionCount,
            isReaction = uiModel.isReaction,
            isLoading = isLoading,
            onClickFunnyButton = onClickFunnyButton,
            onReactionButtonPositioned = onReactionButtonPositioned,
            currentDetailScreenSize = currentDetailScreenSize,
        )
    }
}

private fun Modifier.mapImageSkeletonModifierIfNeed(
    isLoading: Boolean,
    width: Dp,
    height: Dp
): Modifier {
    return if (isLoading) Modifier
        .clip(FarmemeRadius.Radius10.shape)
        .width(width)
        .height(height)
        .showSkeleton(true)
    else this
}

private fun Modifier.mapTextSkeletonModifierIfNeed(
    isLoading: Boolean,
    height: Dp,
    shape: RoundedCornerShape,
): Modifier {
    return if (isLoading) Modifier
        .clip(shape)
        .fillMaxWidth()
        .height(height)
        .showSkeleton(true)
    else this
}

@Composable
private fun DetailImage(
    imageUrl: String,
    isLoading: Boolean,
    saveBitmap: (Bitmap) -> Unit,
    currentDetailScreenSize: DetailScreenSize,
) {
    val (widthDp, heightDp) = currentDetailScreenSize.toImageSize()
    Box(
        modifier = Modifier.clip(FarmemeRadius.Radius10.shape)
    ) {
        AsyncImage(
            model = ImageRequest
                .Builder(LocalContext.current)
                .diskCachePolicy(CachePolicy.ENABLED)
                .memoryCachePolicy(CachePolicy.ENABLED)
                .data(imageUrl)
                .crossfade(true)
                .build(),
            contentDescription = null,
            contentScale = ContentScale.Fit,
            modifier = Modifier
                .background(FarmemeTheme.backgroundColor.black)
                .width(widthDp)
                .height(heightDp)
                .mapImageSkeletonModifierIfNeed(
                    isLoading = isLoading,
                    width = widthDp,
                    height = heightDp,
                ),
            onSuccess = { saveBitmap(it.result.drawable.toBitmap()) }
        )
        FarmemeImageDim()
    }
}

@Composable
internal fun DetailHashTags(
    name: String,
    sourceDescription: String,
    hashTags: ImmutableList<String>,
    isLoading: Boolean,
    onHashTagsClick: () -> Unit,
    currentDetailScreenSize: DetailScreenSize
) {
    val textColor =  if (currentDetailScreenSize == DetailScreenSize.SMALL) FarmemeTheme.textColor.inverse else FarmemeTheme.textColor.primary
    Text(
        modifier = Modifier.mapTextSkeletonModifierIfNeed(
            isLoading = isLoading,
            height = 30.dp,
            shape = FarmemeRadius.Radius4.shape,
        ),
        text = name.truncateDisplayedString(16),
        color = textColor,
        style = FarmemeTheme.typography.heading.large.semibold,
        overflow = TextOverflow.Ellipsis
    )
    Spacer(modifier = Modifier.height(5.dp))
    DetailTags(
        modifier = Modifier.mapTextSkeletonModifierIfNeed(
            isLoading = isLoading,
            height = 18.dp,
            shape = FarmemeRadius.Radius4.shape,
        ),
        hashTags = hashTags.truncateDisplayedList(6),
        onHashTagsClick = onHashTagsClick,
        currentDetailScreenSize = currentDetailScreenSize
    )
    if (sourceDescription.isNotEmpty()) {
        Spacer(modifier = Modifier.height(11.dp))
        Text(
            modifier = Modifier.mapTextSkeletonModifierIfNeed(
                isLoading = isLoading,
                height = 15.dp,
                shape = FarmemeRadius.Radius4.shape
            ),
            text = "출처 : ${sourceDescription.truncateDisplayedString(32)}",
            color = FarmemeTheme.textColor.assistive,
            style = FarmemeTheme.typography.body.xSmall.medium,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
        )
    }
    Spacer(modifier = Modifier.height(20.dp))
}

@Composable
internal fun DetailTags(
    modifier: Modifier,
    hashTags: List<String>,
    onHashTagsClick: () -> Unit,
    currentDetailScreenSize: DetailScreenSize,
) {
    Text(
        modifier = modifier.noRippleClickable(onClick = onHashTagsClick),
        text = hashTags.joinToString(" ") { "#$it" },
        color = if (currentDetailScreenSize == DetailScreenSize.SMALL) FarmemeTheme.textColor.disabled else FarmemeTheme.textColor.tertiary,
        style = FarmemeTheme.typography.body.large.medium,
    )
}

@Composable
private fun DetailFunnyButton(
    modifier: Modifier = Modifier,
    reactionCount: Int,
    isReaction: Boolean,
    isLoading: Boolean,
    onClickFunnyButton: () -> Unit,
    onReactionButtonPositioned: (Offset) -> Unit,
    currentDetailScreenSize: DetailScreenSize,
) {
    val lottieComposition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.lol_move_effect))
    val coroutineScope = rememberCoroutineScope()
    val lottieAnimatable = rememberLottieAnimatable()
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(46.dp)
            .clip(FarmemeRadius.Radius10.shape)
            .background(color = if (currentDetailScreenSize == DetailScreenSize.SMALL) FarmemeTheme.backgroundColor.white else FarmemeTheme.skeletonColor.primary)
            .rippleClickable(
                rippleColor = FarmemeTheme.skeletonColor.secondary,
                isDebounceClick = false,
                onClick = {
                    if (!isLoading) {
                        coroutineScope.launch {
                            lottieAnimatable.animate(composition = lottieComposition)
                        }
                        onClickFunnyButton()
                    }
                }
            )
            .onGloballyPositioned {
                val bound = it.boundsInWindow()
                onReactionButtonPositioned(bound.topLeft)
            },
        contentAlignment = Alignment.Center
    ) {
        Row(
            modifier = modifier,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            if (reactionCount == 0) {
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
                if (isReaction) {
                    LottieAnimation(
                        modifier = Modifier.size(
                            height = 22.dp,
                            width = 44.dp,
                        ),
                        composition = lottieComposition,
                        progress = { lottieAnimatable.progress },
                    )
                } else {
                    FarmemeIcon.KKHorizon(
                        modifier = Modifier.size(
                            height = 22.dp,
                            width = 44.dp
                        )
                    )
                }
                Spacer(modifier = Modifier.width(6.dp))
                Text(
                    text = "+$reactionCount",
                    style = FarmemeTheme.typography.highlight.basic,
                    color = if (isReaction) {
                        FarmemeTheme.textColor.brand
                    } else {
                        FarmemeTheme.textColor.primary
                    }
                )
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
private fun PreviewDetailContent() {
    DetailContent(
        modifier = Modifier,
        uiModel = DetailUiState.PREVIEW_STATE.detailMemeUiModel,
        saveBitmap = {},
        onClickFunnyButton = {},
        onReactionButtonPositioned = { _ -> },
        isLoading = false,
        onHashTagsClick = {},
        currentDetailScreenSize = DetailScreenSize.MEDIUM
    )
}