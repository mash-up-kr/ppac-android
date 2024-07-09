package team.ppac.mypage

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
internal fun MyPageRoute(
    modifier: Modifier = Modifier,
    navigateToDetail: () -> Unit,
    navigateToSetting: () -> Unit,
) {
    MyPageScreen(
        modifier = modifier,
        navigateToDetail = navigateToDetail,
        navigateToSetting = navigateToSetting,
    )
}