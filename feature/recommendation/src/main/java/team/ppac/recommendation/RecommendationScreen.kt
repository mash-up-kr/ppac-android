package team.ppac.recommendation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import team.ppac.designsystem.FarmemeTheme
import team.ppac.designsystem.component.scaffold.FarmemeScaffold
import team.ppac.designsystem.component.scaffold.type.BackgroundColorType

@Composable
internal fun RecommendationScreen(
    modifier: Modifier = Modifier,
) {
    FarmemeScaffold(
        modifier = modifier.fillMaxSize(),
        backgroundColorType = BackgroundColorType.GradientColor(FarmemeTheme.backgroundColor.brandLemonGradient),
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