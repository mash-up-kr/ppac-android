package team.ppac.mypage

import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
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

                    MyPageSideEffect.NavigateToSetting -> navigateToSetting()

                    is MyPageSideEffect.ShowLevelUpSnackBar -> {
                        viewModel.showSnackbar(
                            message = "LV.${sideEffect.level}로 레벨업했어요!",
                            icon = {
                                FarmemeIcon.SuccessFilledBrand(Modifier.size(20.dp))
                            }
                        )
                    }
                }
            }
        }

        LaunchedEffect(key1 = Unit) {
            viewModel.intent(MyPageIntent.InitView)
        }

        DisposableEffect(key1 = Unit) {// TODO(ze-zeh) : viewModel이 초기화 되지 않아서 기존 상태가 남아있는 문제 임시 해결
            onDispose {
                viewModel.intent(MyPageIntent.DisposeView)
            }
        }

        MyPageScreen(
            uiState = uiState,
            onIntent = { myPageIntent ->
                viewModel.intent(myPageIntent)
            },
            onCopyClick = {},
        )
    }
}