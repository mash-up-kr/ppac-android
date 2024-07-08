package team.ppac.recommendation

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.Text
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import team.ppac.designsystem.FarmemeTheme
import team.ppac.designsystem.R
import team.ppac.designsystem.component.button.FarmemeCircleButton
import team.ppac.designsystem.component.button.FarmemeWeakButton
import team.ppac.designsystem.component.scaffold.FarmemeScaffold
import team.ppac.designsystem.component.scaffold.type.BackgroundColorType
import team.ppac.designsystem.foundation.FarmemeIcon
import team.ppac.recommendation.component.HeroModulePager
import team.ppac.recommendation.component.LinearProgressBar

@OptIn(ExperimentalFoundationApi::class)
@Composable
internal fun RecommendationScreen(
    viewModel: RecommendationViewModel = hiltViewModel(),
) {
    FarmemeScaffold(
        modifier = Modifier.fillMaxSize(),
        backgroundColorType = BackgroundColorType.GradientColor(FarmemeTheme.backgroundColor.brandLemonGradient),
        scaffoldState = rememberScaffoldState()
    ) {
        val state by viewModel.state.collectAsState()
        val pagerProgress by remember { mutableFloatStateOf(0.5f) }
        val heroModulePagerState = rememberPagerState {
            state.thisWeekMemes.size
        }

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    brush = FarmemeTheme.backgroundColor.brandLemonGradient,
                )
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
                Row(
                    horizontalArrangement = Arrangement.spacedBy(6.dp),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    FarmemeIcon.CheckRectangle(modifier = Modifier.size(16.dp))
                    LinearProgressBar(
                        modifier = Modifier.width(124.dp),
                        progress = pagerProgress
                    )
                    Text(
                        text = "n개 봤어요",
                        style = FarmemeTheme.typography.body.small.semibold,
                        color = FarmemeTheme.textColor.brand,
                    )
                }
                Spacer(modifier = Modifier.padding(top = 36.dp))
                HeroModulePager(
                    images = state.thisWeekMemes,
                    pagerState = heroModulePagerState,
                )
                Spacer(modifier = Modifier.padding(top = 20.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(4.dp, Alignment.CenterHorizontally)
                ) {
                    listOf("#웃긴", "#놀람", "#동물", "#웃긴", "#놀람", "#동물").forEach {
                        Text(
                            text = it,
                            style = FarmemeTheme.typography.body.medium.medium,
                            color = FarmemeTheme.textColor.secondary,
                        )
                    }
                }
                Spacer(modifier = Modifier.padding(top = 30.dp))
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 30.dp),
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                ) {
                    FarmemeWeakButton(
                        modifier = Modifier.weight(1f),
                        backgroundColor = FarmemeTheme.backgroundColor.white,
                        text = "",
                        textColor = Color.Unspecified,
                        icon = {
                            Row {
                                FarmemeIcon.Lol()
                                FarmemeIcon.SoFunny()
                            }
                        }
                    )
                    FarmemeCircleButton(
                        backgroundColor = FarmemeTheme.backgroundColor.white,
                        icon = { FarmemeIcon.Stroke() },
                    )

                    FarmemeCircleButton(
                        backgroundColor = FarmemeTheme.backgroundColor.white,
                        icon = { FarmemeIcon.Share() },
                    )

                    FarmemeCircleButton(
                        backgroundColor = FarmemeTheme.backgroundColor.white,
                        icon = { FarmemeIcon.BookmarkLine() },
                    )
                }
            }
        }
    }
}
