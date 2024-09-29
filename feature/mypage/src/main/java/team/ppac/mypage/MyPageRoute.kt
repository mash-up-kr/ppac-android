package team.ppac.mypage

import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import kotlinx.coroutines.flow.collectLatest
import team.ppac.analytics.AnalyticsHelper
import team.ppac.analytics.action.CONTENT_TYPE
import team.ppac.analytics.action.MEME_ID
import team.ppac.analytics.action.MEME_TITLE
import team.ppac.analytics.action.MyPageAction
import team.ppac.analytics.action.PAGINATION_COUNT
import team.ppac.analytics.type.ScreenType
import team.ppac.common.android.base.BaseComposable
import team.ppac.common.android.util.ComposableLifecycle
import team.ppac.designsystem.foundation.FarmemeIcon
import team.ppac.mypage.mvi.MyPageIntent
import team.ppac.mypage.mvi.MyPageSideEffect

@Composable
internal fun MyPageRoute(
    analyticsHelper: AnalyticsHelper,
    viewModel: MyPageViewModel = hiltViewModel(),
    navigateToDetail: (String) -> Unit,
    navigateToSetting: () -> Unit,
    navigateToRegister: () -> Unit,
) {
    var lastPage by remember { mutableIntStateOf(0) }

    ComposableLifecycle { _, event ->
        when (event) {
            Lifecycle.Event.ON_START -> {
                analyticsHelper.reportScreen(ScreenType.MY_PAGE)
            }

            Lifecycle.Event.ON_STOP -> {
                analyticsHelper.reportAction(
                    action = MyPageAction.SCROLL,
                    screen = ScreenType.MY_PAGE,
                    params = {
                        param(PAGINATION_COUNT, "$lastPage")
                    }
                )
            }

            else -> {}
        }
    }

    BaseComposable(viewModel = viewModel) { uiState ->
        LaunchedEffect(Unit) {
            viewModel.currentPage.collectLatest { currentPage ->
                lastPage = currentPage
            }
        }

        LaunchedEffect(key1 = viewModel) {
            viewModel.sideEffect.collect { sideEffect ->
                when (sideEffect) {
                    is MyPageSideEffect.NavigateToDetail -> {
                        analyticsHelper.reportAction(
                            action = MyPageAction.CLICK_MEME,
                            screen = ScreenType.MY_PAGE,
                            params = {
                                param(CONTENT_TYPE, sideEffect.contentType)
                            }
                        )

                        navigateToDetail(sideEffect.memeId)
                    }

                    MyPageSideEffect.NavigateToSetting -> {
                        analyticsHelper.reportAction(
                            action = MyPageAction.CLICK_SETTINGS,
                            screen = ScreenType.MY_PAGE
                        )

                        navigateToSetting()
                    }

                    MyPageSideEffect.NavigateToRegister -> {
                        analyticsHelper.reportAction(
                            action = MyPageAction.CLICK_REGISTER,
                            screen = ScreenType.MY_PAGE
                        )

                        navigateToRegister()
                    }

                    is MyPageSideEffect.ShowLevelUpSnackBar -> {
                        viewModel.showSnackbar(
                            message = "LV.${sideEffect.level}로 레벨업했어요!",
                            icon = {
                                FarmemeIcon.SuccessFilledBrand(Modifier.size(20.dp))
                            }
                        )
                    }

                    is MyPageSideEffect.LogClickCopy -> {
                        analyticsHelper.reportAction(
                            action = MyPageAction.CLICK_COPY,
                            screen = ScreenType.MY_PAGE,
                            params = {
                                with(sideEffect.meme) {
                                    param(MEME_ID, id)
                                    param(MEME_TITLE, title)
                                }
                            }
                        )
                    }
                }
            }
        }

        LaunchedEffect(key1 = Unit) {
            viewModel.intent(MyPageIntent.InitView)
        }

        MyPageScreen(
            uiState = uiState,
            onIntent = { myPageIntent ->
                viewModel.intent(myPageIntent)
            },
            savedMemeEventFlow = viewModel.savedMemeEventFlow,
        )
    }
}