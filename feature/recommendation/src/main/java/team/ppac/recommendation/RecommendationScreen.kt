package team.ppac.recommendation

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.Text
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import kotlinx.collections.immutable.toImmutableList
import team.ppac.designsystem.FarmemeTheme
import team.ppac.designsystem.R
import team.ppac.designsystem.component.scaffold.FarmemeScaffold
import team.ppac.designsystem.component.scaffold.type.BackgroundColorType
import team.ppac.recommendation.component.ActionButtons
import team.ppac.recommendation.component.HeroModulePager
import team.ppac.recommendation.component.KeywordsRow
import team.ppac.recommendation.component.SeenMemeProgressBar
import team.ppac.recommendation.mvi.RecommendationIntent

@OptIn(ExperimentalFoundationApi::class)
@Composable
internal fun RecommendationScreen(
    viewModel: RecommendationViewModel = hiltViewModel(),
) {
    val state by viewModel.state.collectAsState()
    val heroModulePagerState = rememberPagerState {
        state.thisWeekMemes.size
    }
    FarmemeScaffold(
        modifier = Modifier.fillMaxSize(),
        backgroundColorType = BackgroundColorType.GradientColor(FarmemeTheme.backgroundColor.brandLemonGradient),
        scaffoldState = rememberScaffoldState()
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .statusBarsPadding()
                .padding(top = 36.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Image(
                painterResource(id = R.drawable.logo_farmeme),
                contentDescription = null,
            )
            Spacer(modifier = Modifier.padding(top = 10.dp))
            Text(
                text = "이번주 이 밈 어때!",
                style = FarmemeTheme.typography.heading.large.bold,
                color = FarmemeTheme.textColor.primary,
            )
            Spacer(modifier = Modifier.padding(top = 16.dp))
            SeenMemeProgressBar(
                seenMemeCount = state.seenMemeCount
            )
            Spacer(modifier = Modifier.padding(top = 8.dp))
            Text(
                text = "밈 보고 레벨 포인트 받아요!",
                style = FarmemeTheme.typography.body.medium.medium,
                color = FarmemeTheme.textColor.secondary,
            )
            Spacer(modifier = Modifier.padding(top = 36.dp))
            if (state.thisWeekMemes.isNotEmpty()) {
                HeroModulePager(
                    memes = state.thisWeekMemes,
                    pagerState = heroModulePagerState,
                    onMovePage = {
                        viewModel.intent(RecommendationIntent.MovePage(it))
                    }
                )
                Spacer(modifier = Modifier.padding(top = 20.dp))
                KeywordsRow(
                    modifier = Modifier.fillMaxWidth(),
                    keywords = state.thisWeekMemes[heroModulePagerState.currentPage].keywords.toImmutableList()
                )
                Spacer(modifier = Modifier.padding(top = 30.dp))
                ActionButtons(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 30.dp),
                    meme = state.thisWeekMemes[heroModulePagerState.currentPage],
                    onClickIntent = viewModel::intent
                )
            }
        }
    }
}
