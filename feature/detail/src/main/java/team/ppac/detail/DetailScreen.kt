package team.ppac.detail

import android.graphics.Bitmap
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import kotlinx.collections.immutable.persistentListOf
import team.ppac.common.android.util.copyImageToClipBoard
import team.ppac.designsystem.component.scaffold.FarmemeScaffold
import team.ppac.designsystem.component.toolbar.FarmemeBackToolBar
import team.ppac.detail.component.DetailBottomBar
import team.ppac.detail.component.DetailContent
import team.ppac.detail.model.DetailMemeUiModel
import team.ppac.detail.mvi.DetailUiState

@Composable
internal fun DetailScreen(
    modifier: Modifier = Modifier,
    uiState: DetailUiState,
    onClickFarmemeButton: (Boolean) -> Unit,
    onClickFunnyButton: () -> Unit,
    onReactionButtonPosition: (Offset) -> Unit,
    onClickBackButton: () -> Unit,
) {

    var context = LocalContext.current
    var bitmap: Bitmap? by remember { mutableStateOf(null) }

    val copyBitmap: () -> Unit = {
        bitmap?.let {
            context.copyImageToClipBoard(it)
        }
    }

    val saveBitmap: (Bitmap) -> Unit = {
        bitmap = it
    }

    FarmemeScaffold(
        modifier = modifier,
        topBar = {
            FarmemeBackToolBar(
                title = "밈 자세히 보기",
                onClickBackIcon = onClickBackButton,
            )
        },
        bottomBar = {
            DetailBottomBar(
                memeId = uiState.memeId,
                isSaved = uiState.detailMemeUiModel.isSavedMeme,
                copyBitmap = copyBitmap,
                onClickFarmemeButton = onClickFarmemeButton,
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
                saveBitmap = saveBitmap,
                onClickFunnyButton = onClickFunnyButton,
                onReactionButtonPositioned = onReactionButtonPosition,
            )
        }
    }
}

@Preview
@Composable
fun PreviewDetailScreen() {
    DetailScreen(
        uiState = DetailUiState(
            memeId = "",
            detailMemeUiModel = DetailMemeUiModel(
                imageUrl = "",
                name = "나는 공부를 찢어",
                hashTags = persistentListOf("#공부", "#학생", "#시험기간", "#힘듦", "#피곤"),
                sourceDescription = "출처에 대한 내용이 들어갑니다.",
                isSavedMeme = false,
                reactionCount = 0,
            ),
        ),
        onClickFarmemeButton = {},
        onClickFunnyButton = {},
        onReactionButtonPosition = { _ -> },
        onClickBackButton = {},
    )
}