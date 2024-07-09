package team.ppac.mypage

import androidx.compose.runtime.Composable

@Composable
internal fun MyPageRoute(
    navigateToDetail: () -> Unit,
    navigateToSetting: () -> Unit,
) {
    MyPageScreen(
        navigateToDetail = navigateToDetail,
        navigateToSetting = navigateToSetting,
    )
}