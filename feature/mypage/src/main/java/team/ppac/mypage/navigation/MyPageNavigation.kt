package team.ppac.mypage.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import team.ppac.analytics.AnalyticsHelper
import team.ppac.mypage.MyPageRoute

const val MY_PAGE_ROUTE = "my_page"

fun NavController.navigateToMyPage(navOptions: NavOptions) = navigate(MY_PAGE_ROUTE, navOptions)

fun NavGraphBuilder.myPageScreen(
    analyticsHelper: AnalyticsHelper,
    navigateToDetail: (String) -> Unit,
    navigateToSetting: () -> Unit,
) {
    composable(
        route = MY_PAGE_ROUTE
    ) {
        MyPageRoute(
            analyticsHelper = analyticsHelper,
            navigateToDetail = navigateToDetail,
            navigateToSetting = navigateToSetting,
        )
    }
}