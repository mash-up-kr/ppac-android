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

@Composable
internal fun DetailScreen() {
    FarmemeScaffold(
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
                .padding(top = 31.dp, bottom = 44.dp)
        )
    }
}

@Preview
@Composable
fun PreviewDetailScreen() {
    DetailScreen()
}