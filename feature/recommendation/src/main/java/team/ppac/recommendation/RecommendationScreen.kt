@file:OptIn(
    ExperimentalMaterialApi::class,
    ExperimentalFoundationApi::class,
    ExperimentalLayoutApi::class
)

package team.ppac.recommendation

import android.graphics.Bitmap
import androidx.compose.animation.Crossfade
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsIgnoringVisibility
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.toMutableStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
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
import kotlinx.coroutines.launch
import team.ppac.common.android.base.BaseComposable
import team.ppac.common.android.component.error.FarmemeErrorScreen
import team.ppac.common.android.util.SkeletonViewType
import team.ppac.common.android.util.copyImageToClipBoard
import team.ppac.common.android.util.shareOneLink
import team.ppac.common.android.util.showSkeleton
import team.ppac.designsystem.FarmemeTheme
import team.ppac.designsystem.R
import team.ppac.designsystem.component.scaffold.FarmemeScaffold
import team.ppac.designsystem.component.scaffold.type.BackgroundColorType
import team.ppac.designsystem.foundation.FarmemeRadius
import team.ppac.domain.model.Meme
import team.ppac.recommendation.component.ActionButtons
import team.ppac.recommendation.component.HeroModulePager
import team.ppac.recommendation.component.KeywordsRow
import team.ppac.recommendation.component.SeenMemeProgressBar
import team.ppac.recommendation.mvi.RecommendationIntent
import team.ppac.recommendation.mvi.RecommendationSideEffect
import kotlin.math.roundToInt

@Composable
internal fun RecommendationScreen(
    viewModel: RecommendationViewModel = hiltViewModel(),
) {
    BaseComposable(viewModel = viewModel) { state ->
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
        val pullRefreshState = rememberPullRefreshState(
            refreshing = state.isRefreshing,
            onRefresh = {
                viewModel.intent(RecommendationIntent.PullRefresh)
            },
        )

        LaunchedEffect(viewModel, memeBitmap) {
            viewModel.sideEffect.collect { sideEffect ->
                when (sideEffect) {
                    RecommendationSideEffect.RunRisingEffect -> {
                        launch {
                            lottieAnimatable.animate(
                                composition = lottieComposition,
                                speed = 1.5f
                            )
                        }
                    }

                    is RecommendationSideEffect.CopyClipBoard -> {
                        memeBitmap[sideEffect.memeIndex]?.let {
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
            modifier = Modifier
                .fillMaxSize()
                .pullRefresh(pullRefreshState),
            backgroundColorType = BackgroundColorType.GradientColor(FarmemeTheme.backgroundColor.brandLemonGradient),
        ) {
            Crossfade(targetState = state.isError) { isError ->
                if (isError) {
                    FarmemeErrorScreen(
                        modifier = Modifier.fillMaxSize(),
                        title = "밈을 불러오지 못 했어요.\n" + "새로고침 해주세요."
                    ) {
                        viewModel.intent(RecommendationIntent.Init)
                    }
                } else {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .verticalScroll(rememberScrollState())
                            .padding(top = with(LocalDensity.current) {
                                WindowInsets.statusBarsIgnoringVisibility
                                    .getTop(this)
                                    .toDp()
                            })
                            .padding(top = 38.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                    ) {
                        Image(
                            painterResource(id = R.drawable.logo_farmeme),
                            contentDescription = null,
                        )
                        Spacer(modifier = Modifier.padding(top = 10.dp))
                        Text(
                            text = "이 번주 이 밈 어때!",
                            style = FarmemeTheme.typography.heading.large.bold,
                            color = FarmemeTheme.textColor.primary,
                        )
                        Spacer(modifier = Modifier.padding(top = 16.dp))
                        if (state.isLoading) {
                            Spacer(modifier = Modifier.padding(top = 20.dp))
                            Box(
                                modifier = Modifier
                                    .clip(RoundedCornerShape(4.dp))
                                    .showSkeleton(
                                        isLoading = state.isLoading,
                                        viewType = SkeletonViewType.Home
                                    )
                                    .size(
                                        width = 200.dp,
                                        height = 16.dp,
                                    )
                            )
                        } else {
                            SeenMemeProgressBar(
                                seenMemeCount = state.seenMemeCount,
                            )
                            Spacer(modifier = Modifier.padding(top = 8.dp))
                            Text(
                                text = when {
                                    state.seenMemeCount == 5 -> {
                                        "완밈! 다음 주 밈도 기대해 주세요"
                                    }

                                    state.level >= 2 -> {
                                        "추천 밈 둘러보세요!"
                                    }

                                    else -> {
                                        "밈 보고 레벨 포인트 받아요!"
                                    }
                                },
                                style = FarmemeTheme.typography.body.medium.medium,
                                color = FarmemeTheme.textColor.secondary,
                            )
                        }
                        Spacer(modifier = Modifier.padding(top = 32.dp))
                        when {
                            state.thisWeekMemes.isNotEmpty() -> {
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

                            state.isLoading -> {
                                ContentLoadingSkeleton(isLoading = state.isLoading)
                            }
                        }
                    }
                    Box(
                        modifier = Modifier.fillMaxWidth(),
                        contentAlignment = Alignment.Center,
                    ) {
                        PullRefreshIndicator(
                            refreshing = state.isRefreshing,
                            state = pullRefreshState,
                        )
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
        }
    }
}

@Composable
private fun ContentLoadingSkeleton(
    isLoading: Boolean,
) {
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(20.dp))
            .showSkeleton(
                isLoading = isLoading,
                viewType = SkeletonViewType.Home
            )
            .size(width = 270.dp, height = 310.dp)
    )
    Spacer(modifier = Modifier.height(20.dp))
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(4.dp))
            .showSkeleton(
                isLoading = isLoading,
                viewType = SkeletonViewType.Home
            )
            .size(width = 200.dp, height = 16.dp)
    )
    Spacer(modifier = Modifier.height(30.dp))
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 30.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        Box(
            modifier = Modifier
                .clip(FarmemeRadius.Radius40.shape)
                .showSkeleton(
                    isLoading = isLoading,
                    viewType = SkeletonViewType.Home
                )
                .weight(1f)
                .height(50.dp)
        )
        repeat(3) {
            Box(
                modifier = Modifier
                    .clip(FarmemeRadius.Radius40.shape)
                    .showSkeleton(
                        isLoading = isLoading,
                        viewType = SkeletonViewType.Home
                    )
                    .size(50.dp)
            )
        }
    }
}
