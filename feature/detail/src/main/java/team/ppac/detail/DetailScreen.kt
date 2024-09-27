package team.ppac.detail

import android.graphics.Bitmap
import android.os.Build
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.BlurEffect
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import coil.compose.AsyncImage
import coil.request.CachePolicy
import coil.request.ImageRequest
import kotlinx.collections.immutable.persistentListOf
import team.ppac.designsystem.FarmemeTheme
import team.ppac.designsystem.component.scaffold.FarmemeScaffold
import team.ppac.designsystem.component.toolbar.FarmemeBackToolBar
import team.ppac.detail.component.DetailBottomBar
import team.ppac.detail.component.DetailContent
import team.ppac.detail.model.DetailMemeUiModel
import team.ppac.detail.mvi.DetailIntent
import team.ppac.detail.mvi.DetailUiState

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
) {
    FarmemeScaffold(
        modifier = modifier,
        topBar = {
            FarmemeBackToolBar(
                title = "밈 자세히 보기",
                onBackIconClick = onClickBackButton,
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
            DetailContent(
                uiModel = uiState.detailMemeUiModel,
                isLoading = uiState.isLoading,
                saveBitmap = saveBitmap,
                onClickFunnyButton = onClickFunnyButton,
                onReactionButtonPositioned = onReactionButtonPosition,
                onHashTagsClick = onHashTagsClick
            )
        }
    }
}

@Preview
@Composable
private fun PreviewDetailScreen() {
    DetailScreen(
        uiState = DetailUiState(
            memeId = "",
            detailMemeUiModel = DetailMemeUiModel(
                imageUrl = "",
                name = "나는 공부를 찢어",
                hashTags = persistentListOf("공부", "학생", "시험기간", "힘듦", "피곤"),
                sourceDescription = "출처에 대한 내용이 들어갑니다.",
                isSavedMeme = false,
                reactionCount = 0,
                isReaction = false,
            ),
            isError = false,
            isLoading = false,
        ),
        onClickFunnyButton = {},
        onReactionButtonPosition = { _ -> },
        onClickBackButton = {},
        onClickButtonButtons = {},
        saveBitmap = {},
        onHashTagsClick = {}
    )
}