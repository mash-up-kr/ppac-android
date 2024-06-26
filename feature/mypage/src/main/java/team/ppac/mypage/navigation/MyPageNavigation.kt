package team.ppac.mypage.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import team.ppac.mypage.MyPageRoute

const val MY_PAGE_ROUTE = "mypage"

fun NavGraphBuilder.myPageScreen() {
    composable(
        route = MY_PAGE_ROUTE
    ) {
        MyPageRoute()
    }
}