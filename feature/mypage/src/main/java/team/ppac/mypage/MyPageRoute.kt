package team.ppac.mypage

import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import team.ppac.common.android.base.BaseComposable
import team.ppac.designsystem.foundation.FarmemeIcon
import team.ppac.mypage.mvi.MyPageIntent
import team.ppac.mypage.mvi.MyPageSideEffect

@Composable
internal fun MyPageRoute(
    viewModel: MyPageViewModel = hiltViewModel(),
    navigateToDetail: (String) -> Unit,
    navigateToSetting: () -> Unit,
) {
    BaseComposable(viewModel = viewModel) { uiState ->
        LaunchedEffect(key1 = viewModel) {
            viewModel.sideEffect.collect { sideEffect ->
                when (sideEffect) {
                    is MyPageSideEffect.NavigateToDetail -> navigateToDetail(sideEffect.memeId)
                    is MyPageSideEffect.NavigateToSetting -> navigateToSetting()
                }
            }
        }

        MyPageScreen(
            uiState = uiState,
            onRefreshData = {
                viewModel.intent(MyPageIntent.RefreshData)
            },
            onSettingClick = {
                viewModel.intent(MyPageIntent.ClickSettingButton)
            },
            onCopyClick = {
                viewModel.showSnackbar(
                    message = "이미지를 클립보드에 복사했어요",
                    icon = {
                        FarmemeIcon.CopyFilled(Modifier.size(20.dp))
                    }
                )
            },
            onRecentMemeClick = { memeId ->
                viewModel.intent(MyPageIntent.ClickRecentMemeItem(memeId = memeId))
            },
            onSavedMemeClick = { memeId ->
                viewModel.intent(MyPageIntent.ClickSavedMemeItem(memeId = memeId))
            },
        )
    }
}