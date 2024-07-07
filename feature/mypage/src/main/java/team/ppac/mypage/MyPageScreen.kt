package team.ppac.mypage

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Text
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import team.ppac.designsystem.FarmemeTheme
import team.ppac.designsystem.R
import team.ppac.designsystem.component.scaffold.FarmemeScaffold
import team.ppac.designsystem.component.scaffold.type.BackgroundColorType
import team.ppac.designsystem.component.tabbar.TabBarHeight
import team.ppac.designsystem.component.toolbar.FarmemeActionToolBar
import team.ppac.mypage.component.MyPageLevelBox
import team.ppac.mypage.component.MyPageProgressBar
import team.ppac.mypage.component.MyPageSpeechBubble
import team.ppac.mypage.component.RecentMemeContent
import team.ppac.mypage.model.MyPageLevel
import team.ppac.mypage.model.MyPageUiModel
import team.ppac.mypage.mvi.MyPageIntent

@Composable
internal fun MyPageScreen(
    viewModel: MyPageViewModel = hiltViewModel(),
) {
    val state by viewModel.state.collectAsState()
    val myPageUiModel = state.myPageUiModel
    val recentMemes = state.recentMemes
    val savedMemes = state.savedMemes

    FarmemeScaffold(
        modifier = Modifier.fillMaxSize(),
        backgroundColorType = BackgroundColorType.GradientColor(FarmemeTheme.backgroundColor.brandWhiteGradient),
        scaffoldState = rememberScaffoldState()
    ) {
        LazyColumn(
            horizontalAlignment = Alignment.CenterHorizontally,
            contentPadding = PaddingValues(bottom = TabBarHeight),
        ) {
            item {
                FarmemeActionToolBar(onClickActionIcon = {
                    viewModel.intent(MyPageIntent.ClickSettingButton)
                })
            }
            item { MyPageBody(myPageUiModel = myPageUiModel) }
            item {
                MyPageProgressBar(
                    modifier = Modifier.padding(horizontal = 20.dp),
                    myPageUiModel = myPageUiModel,
                )
            }
            item {
                MyPageLevelBox(
                    modifier = Modifier.padding(
                        start = 20.dp,
                        top = 16.dp,
                        end = 20.dp,
                        bottom = 40.dp,
                    ),
                    myPageUiModel = myPageUiModel,
                )
            }
            item { RecentMemeContent(recentMemes = recentMemes) }
        }
    }
}

@Composable
private fun MyPageBody(
    modifier: Modifier = Modifier,
    myPageUiModel: MyPageUiModel,
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Spacer(modifier = Modifier.height(8.dp))
        MyPageSpeechBubble()
        Spacer(modifier = Modifier.height(5.dp))
        Image(
            painter = painterResource(
                when (myPageUiModel.userLevel) {
                    MyPageLevel.LEVEL1 -> R.drawable.img_character_level_1
                    MyPageLevel.LEVEL2 -> R.drawable.img_character_level_2
                    MyPageLevel.LEVEL3 -> R.drawable.img_character_level_3
                    MyPageLevel.LEVEL4 -> R.drawable.img_character_level_4
                }
            ),
            contentDescription = null,
        )
        Spacer(modifier = Modifier.height(30.dp))
        Text(
            text = myPageUiModel.userLevel.title,
            color = FarmemeTheme.textColor.primary,
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