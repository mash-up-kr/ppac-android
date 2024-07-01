package team.ppac.mypage

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Text
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import team.ppac.designsystem.FarmemeTheme
import team.ppac.designsystem.R
import team.ppac.designsystem.component.button.FarmemeImageButton
import team.ppac.designsystem.component.scaffold.FarmemeScaffold
import team.ppac.designsystem.component.scaffold.type.BackgroundColorType
import team.ppac.designsystem.foundation.FarmemeIcon
import team.ppac.mypage.component.MyPageLevelBox
import team.ppac.mypage.component.MyPageProgressBar
import team.ppac.mypage.component.MyPageSpeechBubble
import team.ppac.mypage.model.MyPageLevel

@Composable
internal fun MyPageScreen(
    modifier: Modifier = Modifier
) {
    // 임시 데이터
    val level = MyPageLevel.LEVEL3
    val count = 15

    FarmemeScaffold(
        modifier = modifier.fillMaxSize(),
        backgroundColorType = BackgroundColorType.GradientColor(FarmemeTheme.backgroundColor.brandWhiteGradient),
        scaffoldState = rememberScaffoldState()
    ) {
        LazyColumn(
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            item {// TODO : App bar로 교체 예정
                Box(

                    modifier = Modifier.fillMaxWidth(),
                    contentAlignment = Alignment.CenterEnd,
                ) {
                    FarmemeImageButton(
                        modifier = Modifier.padding(top = 15.dp, end = 20.dp, bottom = 15.dp),
                        icon = { FarmemeIcon.Setting(Modifier.size(20.dp)) }
                    )
                }
            }
            item { MyPageBody(level = level) }
            item {
                MyPageProgressBar(level = level, count = count)
            }
            item {
                MyPageLevelBox(level = level, count = count)
            }
            item {
                Spacer(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(10.dp)
                        .background(FarmemeTheme.skeletonColor.primary)
                )
            }
        }
    }
}

@Composable
private fun MyPageBody(
    modifier: Modifier = Modifier,
    level: MyPageLevel,
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        MyPageSpeechBubble()
        Spacer(modifier = Modifier.height(5.dp))
        Image(
            painter = painterResource(
                when (level) {
                    MyPageLevel.LEVEL1 -> R.drawable.img_character_level_1
                    MyPageLevel.LEVEL2 -> R.drawable.img_character_level_1
                    MyPageLevel.LEVEL3 -> R.drawable.img_character_level_1
                    MyPageLevel.LEVEL4 -> R.drawable.img_character_level_1
                }
            ),
            contentDescription = null,
        )
        Spacer(modifier = Modifier.height(30.dp))
        Text(
            text = level.title,
            style = FarmemeTheme.typography.highlight.normal,
        )
        Spacer(modifier = Modifier.height(30.dp))
    }
}

@Preview
@Composable
private fun MyPageScreenPreview() {
    MyPageScreen()
}