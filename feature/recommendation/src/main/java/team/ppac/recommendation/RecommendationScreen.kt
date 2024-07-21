package team.ppac.recommendation

import android.graphics.Bitmap
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsIgnoringVisibility
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.Text
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.toMutableStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.rememberLottieAnimatable
import com.airbnb.lottie.compose.rememberLottieComposition
import kotlinx.collections.immutable.toImmutableList
import team.ppac.common.android.util.copyImageToClipBoard
import team.ppac.common.android.util.shareOneLink
import team.ppac.designsystem.FarmemeTheme
import team.ppac.designsystem.R
import team.ppac.designsystem.component.scaffold.FarmemeScaffold
import team.ppac.designsystem.component.scaffold.type.BackgroundColorType
import team.ppac.domain.model.Meme
import team.ppac.recommendation.component.ActionButtons
import team.ppac.recommendation.component.HeroModulePager
import team.ppac.recommendation.component.KeywordsRow
import team.ppac.recommendation.component.SeenMemeProgressBar
import team.ppac.recommendation.mvi.RecommendationIntent
import team.ppac.recommendation.mvi.RecommendationSideEffect
import kotlin.math.roundToInt


@OptIn(ExperimentalFoundationApi::class, ExperimentalLayoutApi::class)
@Composable
internal fun RecommendationScreen(
    viewModel: RecommendationViewModel = hiltViewModel(),
) {
    val state by viewModel.state.collectAsState()
    val heroModulePagerState = rememberPagerState {
        state.thisWeekMemes.size
    }
    val lottieComposition by rememberLottieComposition(
        LottieCompositionSpec.RawRes(R.raw.lol_rising_effect)
    )
    val lottieAnimatable = rememberLottieAnimatable()
    var lottiePosition by remember { mutableStateOf(Offset.Zero) }
    val context = LocalContext.current
    val memeBitmap = remember(state.thisWeekMemes.size) {
        state.thisWeekMemes.map<Meme, Bitmap?> { null }.toMutableStateList()
    }

    LaunchedEffect(viewModel, memeBitmap) {
        viewModel.sideEffect.collect { sideEffect ->
            when (sideEffect) {
                RecommendationSideEffect.RunRisingEffect -> {
                    lottieAnimatable.animate(
                        composition = lottieComposition,
                        speed = 1.5f
                    )
                }

                is RecommendationSideEffect.CopyClipBoard -> {
                    memeBitmap[sideEffect.memesIndex]?.let {
                        context.copyImageToClipBoard(bitmap = it)
                    }
                }

                is RecommendationSideEffect.ShareLink -> {
                    context.shareOneLink(sideEffect.memeId)
                }
            }
        }
    }

    FarmemeScaffold(
        modifier = Modifier.fillMaxSize(),
        backgroundColorType = BackgroundColorType.GradientColor(FarmemeTheme.backgroundColor.brandLemonGradient),
        scaffoldState = rememberScaffoldState()
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = with(LocalDensity.current) {
                    WindowInsets.statusBarsIgnoringVisibility
                        .getTop(this)
                        .toDp()
                })
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
                    onMovePage = { page, meme ->
                        viewModel.intent(RecommendationIntent.MovePage(meme, page))
                    },
                    onLoadMeme = { index, bitmap ->
                        memeBitmap[index] = bitmap
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
                    onClickIntent = viewModel::intent,
                    page = heroModulePagerState.currentPage,
                    onReactionButtonPositioned = {
                        lottiePosition = it
                    }
                )
            }
        }

        LottieAnimation(
            modifier = Modifier
                .size(200.dp)
                .offset {
                    with(lottiePosition) {
                        // ㅋㅋ 버튼의 좌상단 기준으로 사이즈 참고하여 Offset 위치 조정
                        IntOffset(
                            x = x.roundToInt() - 30.dp.roundToPx(),
                            y = y.roundToInt() - 200.dp.roundToPx()
                        )
                    }
                },
            composition = lottieComposition,
            progress = { lottieAnimatable.progress },
        )
    }
}
