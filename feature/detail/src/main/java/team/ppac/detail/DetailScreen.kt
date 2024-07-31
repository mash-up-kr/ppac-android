package team.ppac.detail

import android.graphics.Bitmap
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.tooling.preview.Preview
import kotlinx.collections.immutable.persistentListOf
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
) {
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
                onClickBottomButtons = onClickButtonButtons,
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
            isError = false,
            isLoading = false,
        ),
        onClickFunnyButton = {},
        onReactionButtonPosition = { _ -> },
        onClickBackButton = {},
        onClickButtonButtons = {},
        saveBitmap = {},
    )
}