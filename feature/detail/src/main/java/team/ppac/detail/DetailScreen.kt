package team.ppac.detail

import androidx.compose.foundation.layout.padding
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
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
) {
    FarmemeScaffold(
        modifier = modifier,
        scaffoldState = rememberScaffoldState(),
        topBar = {
            FarmemeBackToolBar(
                title = "밈 자세히 보기",
                onClickBackIcon = {}
            )
        },
        bottomBar = { DetailBottomBar() },
    ) { innerPadding ->
        DetailContent(
            modifier = Modifier
                .padding(innerPadding)
                .padding(top = 31.dp, bottom = 44.dp),
            uiModel = uiState.detailMemeUiModel
        )
    }
}

@Preview
@Composable
fun PreviewDetailScreen() {
    DetailScreen(
        uiState = DetailUiState(
            detailMemeUiModel = DetailMemeUiModel(
                imageUrl = "",
                name = "나는 공부를 찢어",
                hashTags = listOf("#공부", "#학생", "#시험기간", "#힘듦", "#피곤"),
                sourceDescription = "출처: 출처에 대한 내용이 들어갑니다."
            )
        )
    )
}