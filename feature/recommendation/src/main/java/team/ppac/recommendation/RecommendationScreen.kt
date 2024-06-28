package team.ppac.recommendation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import team.ppac.designsystem.component.scaffold.FarmemeScaffold

@Composable
internal fun RecommendationScreen(
    modifier: Modifier = Modifier
) {
    FarmemeScaffold(
        modifier = modifier.fillMaxSize(),
        isGradientBackgroundColor = true,
        scaffoldState = rememberScaffoldState()
    ) {
        Text(text = "추천밈 스크린 일걸?ㅋㅅㅋ")
    }
}

@Preview
@Composable
private fun MyPageScreenPreview() {
    RecommendationScreen()
}