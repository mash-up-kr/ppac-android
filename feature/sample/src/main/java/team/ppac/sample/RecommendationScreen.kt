@file:OptIn(ExperimentalFoundationApi::class)

package team.ppac.sample

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import team.ppac.designsystem.FarmemeTheme
import team.ppac.designsystem.foundation.FarmemeIcon
import team.ppac.designsystem.foundation.Lemon10
import team.ppac.designsystem.foundation.Orange10
import team.ppac.feature.sample.R

@Composable
internal fun RecommendationScreen(viewModel: SampleViewModel = hiltViewModel()) {
    val state by viewModel.state.collectAsState()
    val pagerProgress by remember { mutableFloatStateOf(0.5f) }
    val heroModulePagerState = rememberPagerState {
        state.images.size
    }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    listOf(Orange10, Lemon10)
                ),
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
                painterResource(id = R.drawable.logo),
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
                images = state.images,
                pagerState = heroModulePagerState,
            )
        }
    }
}
