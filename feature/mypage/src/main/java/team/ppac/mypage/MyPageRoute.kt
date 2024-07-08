package team.ppac.mypage

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
internal fun MyPageRoute(
    modifier: Modifier = Modifier,
    navigateToDetail: () -> Unit,
) {
    MyPageScreen(navigateToDetail = navigateToDetail)
}