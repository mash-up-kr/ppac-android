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
import androidx.compose.foundation.layout.systemBarsPadding
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import com.airbnb.lottie.LottieComposition
import com.airbnb.lottie.compose.LottieAnimatable
import com.airbnb.lottie.compose.LottieAnimation
import kotlinx.collections.immutable.toImmutableList
import team.ppac.common.android.component.error.FarmemeErrorScreen
import team.ppac.common.android.util.SkeletonViewType
import team.ppac.common.android.util.showSkeleton
import team.ppac.designsystem.FarmemeTheme
import team.ppac.designsystem.R
import team.ppac.designsystem.component.scaffold.FarmemeScaffold
import team.ppac.designsystem.component.scaffold.type.BackgroundColorType
import team.ppac.designsystem.component.tabbar.TabBarHeight
import team.ppac.designsystem.foundation.FarmemeRadius
import team.ppac.designsystem.util.extension.noRippleClickable
import team.ppac.domain.model.Meme
import team.ppac.recommendation.component.ActionButtons
import team.ppac.recommendation.component.HeroModulePager
import team.ppac.recommendation.component.KeywordsRow
import team.ppac.recommendation.component.UploadButton
import team.ppac.recommendation.mvi.RecommendationIntent
import team.ppac.recommendation.mvi.RecommendationState
import kotlin.math.roundToInt

@OptIn(
    ExperimentalFoundationApi::class, ExperimentalMaterialApi::class,
    ExperimentalLayoutApi::class
)
@Composable
internal fun RecommendationScreen(
    state: RecommendationState,
    lottieAnimatable: LottieAnimatable,
    lottieComposition: LottieComposition?,
    onPullToRefresh: () -> Unit,
    onRetryClick: () -> Unit,
    onLoadMeme: (Int, Bitmap) -> Unit,
    onScrollPager: (Int, Meme) -> Unit,
    onUpload: () -> Unit,
    onActionButtonsIntentClick: (RecommendationIntent.ClickButton) -> Unit,
) {
    val heroModulePagerState = rememberPagerState { state.thisWeekMemes.size }
    var lottiePosition by remember { mutableStateOf(Offset.Zero) }
    val pullRefreshState = rememberPullRefreshState(
        refreshing = state.isRefreshing,
        onRefresh = onPullToRefresh,
    )

    FarmemeScaffold(
        modifier = Modifier
            .fillMaxSize()
            .pullRefresh(pullRefreshState),
        backgroundColorType = BackgroundColorType.GradientColor(FarmemeTheme.backgroundColor.brandLemonGradient),
    ) {
        Crossfade(targetState = state.isError) { isError ->
            if (isError) {
                FarmemeErrorScreen(
                    modifier = Modifier
                        .fillMaxSize()
                        .systemBarsPadding()
                        .padding(bottom = TabBarHeight),
                    title = "밈을 불러오지 못 했어요.\n" + "새로고침 해주세요.",
                    onRetryClick = onRetryClick
                )
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
                        .padding(
                            top = 38.dp,
                            bottom = TabBarHeight + 48.dp
                        ),
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    Image(
                        painterResource(id = R.drawable.logo_farmeme),
                        contentDescription = null,
                    )
                    Spacer(modifier = Modifier.padding(top = 12.dp))
                    Text(
                        text = "NEW! 따끈따끈한 밈",
                        style = FarmemeTheme.typography.heading.large.bold,
                        color = FarmemeTheme.textColor.primary,
                    )
                    Spacer(modifier = Modifier.padding(top = 4.dp))
                    Text(
                        text = "최근에 사람들이 올린 밈 구경하세요.",
                        style = FarmemeTheme.typography.body.medium.medium,
                        color = FarmemeTheme.textColor.secondary,
                    )
                    Spacer(modifier = Modifier.padding(top = 20.dp))
                    if (state.isLoading) {
                        Box(
                            modifier = Modifier
                                .clip(RoundedCornerShape(10.dp))
                                .showSkeleton(
                                    isLoading = state.isLoading,
                                    viewType = SkeletonViewType.Home
                                )
                                .size(
                                    width = 130.dp,
                                    height = 36.dp,
                                )
                        )
                    } else {
                        UploadButton(
                            onClick = {
                                onUpload()
                            }
                        )
                    }
                    Spacer(modifier = Modifier.padding(top = 28.dp))
                    when {
                        state.thisWeekMemes.isNotEmpty() -> {
                            HeroModulePager(
                                memes = state.thisWeekMemes,
                                pagerState = heroModulePagerState,
                                onMovePage = onScrollPager,
                                onLoadMeme = onLoadMeme
                            )
                            Spacer(modifier = Modifier.padding(top = 16.dp))
                            Text(
                                text= state.thisWeekMemes[heroModulePagerState.currentPage].title,
                                style = FarmemeTheme.typography.heading.small.medium,
                                color = FarmemeTheme.textColor.primary,
                            )
                            Spacer(modifier = Modifier.padding(top = 4.dp))
                            KeywordsRow(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .noRippleClickable(
                                        onClick = {
                                            onActionButtonsIntentClick(RecommendationIntent.ClickButton.HashTags)
                                        }
                                    ),
                                keywords = state.thisWeekMemes[heroModulePagerState.currentPage].keywords.toImmutableList()
                            )
                            Spacer(modifier = Modifier.padding(top = 30.dp))
                            ActionButtons(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(horizontal = 30.dp),
                                meme = state.thisWeekMemes[heroModulePagerState.currentPage],
                                onClickIntent = onActionButtonsIntentClick,
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
