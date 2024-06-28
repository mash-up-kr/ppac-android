package team.ppac.search

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import team.ppac.designsystem.FarmemeTheme
import team.ppac.designsystem.component.scaffold.FarmemeScaffold

@Composable
internal fun SearchScreen(
    modifier: Modifier = Modifier,
) {
    FarmemeScaffold(
        modifier = modifier.fillMaxSize(),
        isGradientBackground = false,
        scaffoldState = rememberScaffoldState()
    ) {
        Text(text = "검색 스크린 일걸?ㅋㅅㅋ")
    }
}

@Preview
@Composable
private fun MyPageScreenPreview() {
    SearchScreen()
}