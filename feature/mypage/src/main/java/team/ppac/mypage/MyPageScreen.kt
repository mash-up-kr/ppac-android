package team.ppac.mypage

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Text
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import team.ppac.designsystem.FarmemeTheme
import team.ppac.designsystem.component.button.FarmemeImageButton
import team.ppac.designsystem.component.scaffold.FarmemeScaffold
import team.ppac.designsystem.component.scaffold.type.BackgroundColorType
import team.ppac.designsystem.foundation.FarmemeIcon
import team.ppac.mypage.component.MyPageLevel
import team.ppac.mypage.component.MyPageProgressBar

@Composable
internal fun MyPageScreen(
    modifier: Modifier = Modifier
) {
    FarmemeScaffold(
        modifier = modifier.fillMaxSize(),
        backgroundColorType = BackgroundColorType.GradientColor(FarmemeTheme.backgroundColor.brandWhiteGradient),
        scaffoldState = rememberScaffoldState()
    ) {
        Column {
            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.CenterEnd,
            ) {
                FarmemeImageButton(
                    modifier = Modifier.padding(top = 15.dp, end = 20.dp, bottom = 15.dp),
                    icon = { FarmemeIcon.Setting(Modifier.size(20.dp)) }
                )
            }
            Box( // TODO : character 들어갈 곳
                modifier = Modifier
                    .fillMaxWidth()
                    .height(260.dp)
                    .background(Color.White)
            )
            Text(
                text = "LV.1 호기심 많은 밈린이",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 30.dp),
                textAlign = TextAlign.Center,
                style = FarmemeTheme.typography.highlight.normal,
            )
            MyPageProgressBar(
                progress = 0.0f,
                level = 1,
            )
            Spacer(modifier = Modifier.height(16.dp))
            MyPageLevel(
                modifier = Modifier.padding(horizontal = 20.dp),
                title = "밈 20번 보기",
                count = 1,
                step = 2,
            )
            Spacer(modifier = Modifier.height(40.dp))
            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(10.dp)
                    .background(FarmemeTheme.skeletonColor.primary)
            )
        }
    }
}

@Preview
@Composable
private fun MyPageScreenPreview() {
    MyPageScreen()
}