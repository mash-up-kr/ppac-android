package team.ppac.mypage.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import team.ppac.mypage.MyPageRoute

const val MY_PAGE_ROUTE = "mypage"

fun NavController.navigateToMyPage(navOptions: NavOptions) = navigate(MY_PAGE_ROUTE, navOptions)

fun NavGraphBuilder.myPageScreen() {
    composable(
        route = MY_PAGE_ROUTE
    ) {
        MyPageRoute()
    }
}