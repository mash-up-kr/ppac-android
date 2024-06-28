package team.ppac.mypage

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import team.ppac.designsystem.FarmemeTheme
import team.ppac.designsystem.component.scaffold.FarmemeScaffold

@Composable
internal fun MyPageScreen(
    modifier: Modifier = Modifier
) {
    FarmemeScaffold(
        modifier = modifier.fillMaxSize(),
        isGradientBackgroundColor = false,
        backgroundColor = FarmemeTheme.backgroundColor.white,
        scaffoldState = rememberScaffoldState()
    ) {
        Text(text = "마이페이지 일걸?ㅋㅅㅋ")
    }
}

@Preview
@Composable
private fun MyPageScreenPreview() {
    MyPageScreen()
}