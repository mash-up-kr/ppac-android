package team.ppac.mypage

import androidx.compose.runtime.Composable

@Composable
internal fun MyPageRoute(
    navigateToDetail: (String) -> Unit,
    navigateToSetting: () -> Unit,
) {
    MyPageScreen(
        navigateToDetail = navigateToDetail,
        navigateToSetting = navigateToSetting,
    )
}