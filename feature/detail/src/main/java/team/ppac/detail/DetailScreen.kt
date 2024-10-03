package team.ppac.detail

import android.graphics.Bitmap
import android.os.Build
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.BlurEffect
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.CachePolicy
import coil.request.ImageRequest
import team.ppac.designsystem.FarmemeTheme
import team.ppac.designsystem.component.scaffold.FarmemeScaffold
import team.ppac.designsystem.component.toolbar.FarmemeToolbar
import team.ppac.designsystem.foundation.FarmemeIcon
import team.ppac.designsystem.foundation.FarmemeRadius
import team.ppac.designsystem.util.extension.noRippleClickable
import team.ppac.detail.component.DetailBottomBar
import team.ppac.detail.component.DetailContent
import team.ppac.detail.mvi.DetailIntent
import team.ppac.detail.mvi.DetailUiState
import team.ppac.detail.mvi.DetailUiState.Companion.PREVIEW_STATE
import team.ppac.detail.util.DetailScreenSize

@Composable
internal fun DetailScreen(
    modifier: Modifier = Modifier,
    uiState: DetailUiState,
    onClickFunnyButton: () -> Unit,
    onReactionButtonPosition: (Offset) -> Unit,
    onClickBackButton: () -> Unit,
    onClickButtonButtons: (DetailIntent.ClickBottomButton) -> Unit,
    saveBitmap: (bitmap: Bitmap) -> Unit,
    onHashTagsClick: () -> Unit,
    currentDetailScreenSize: DetailScreenSize,
    onOptionClick: () -> Unit,
) {
    FarmemeScaffold(
        modifier = modifier,
        topBar = {
            FarmemeToolbar(
                title = "밈 자세히 보기",
                navigationIcon = {
                    FarmemeIcon.Back(
                        modifier = Modifier
                            .size(20.dp)
                            .noRippleClickable(onClick = onClickBackButton)
                    )
                },
                actionIcon = {
                    FarmemeIcon.StrokeDot(
                        modifier = Modifier
                            .size(20.dp)
                            .noRippleClickable(onClick = onOptionClick)
                    )
                }
            )
        },
        backgroundImage = {
            AsyncImage(
                model = ImageRequest
                    .Builder(LocalContext.current)
                    .diskCachePolicy(CachePolicy.ENABLED)
                    .memoryCachePolicy(CachePolicy.ENABLED)
                    .data(uiState.detailMemeUiModel.imageUrl)
                    .crossfade(true)
                    .build(),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxSize()
                    .graphicsLayer {
                        alpha = 0.4f
                    }
                    .background(
                        color = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
                            FarmemeTheme.backgroundColor.white.copy(alpha = 0.3f)
                        } else {
                            FarmemeTheme.backgroundColor.white.copy(alpha = 0.4f)
                        }
                    )
                    .graphicsLayer {
                        renderEffect =
                            BlurEffect(
                                50f,
                                50f,
                            )
                    }
            )
        },
        bottomBar = {
            DetailBottomBar(
                memeId = uiState.memeId,
                isSaved = uiState.detailMemeUiModel.isSavedMeme,
                onClickBottomButtons = onClickButtonButtons,
                isLoading = uiState.isLoading,
            )
        },
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Box(
                modifier = modifier
                    .border(
                        width = 2.dp,
                        color = FarmemeTheme.borderColor.primary,
                        shape = FarmemeRadius.Radius20.shape,
                    )
                    .clip(FarmemeRadius.Radius20.shape)
                    .background(FarmemeTheme.backgroundColor.white),
            ) {
                if (currentDetailScreenSize == DetailScreenSize.SMALL) {
                    Box(
                        modifier = Modifier.padding(10.dp),
                        contentAlignment = Alignment.BottomCenter
                    ) {
                        DetailContent(
                            uiModel = uiState.detailMemeUiModel,
                            isLoading = uiState.isLoading,
                            saveBitmap = saveBitmap,
                            onClickFunnyButton = onClickFunnyButton,
                            onReactionButtonPositioned = onReactionButtonPosition,
                            onHashTagsClick = onHashTagsClick,
                            currentDetailScreenSize = currentDetailScreenSize
                        )
                    }
                } else {
                    Column(
                        modifier = Modifier.padding(10.dp),
                    ) {
                        DetailContent(
                            uiModel = uiState.detailMemeUiModel,
                            isLoading = uiState.isLoading,
                            saveBitmap = saveBitmap,
                            onClickFunnyButton = onClickFunnyButton,
                            onReactionButtonPositioned = onReactionButtonPosition,
                            onHashTagsClick = onHashTagsClick,
                            currentDetailScreenSize = currentDetailScreenSize
                        )
                    }
                }
            }
        }
    }
}

@Preview
@Composable
private fun PreviewDetailScreen() {
    DetailScreen(
        uiState = PREVIEW_STATE,
        onClickFunnyButton = {},
        onReactionButtonPosition = { _ -> },
        onClickBackButton = {},
        onClickButtonButtons = {},
        saveBitmap = {},
        onHashTagsClick = {},
        onOptionClick = {},
        currentDetailScreenSize = DetailScreenSize.MEDIUM
    )
}