package team.ppac.search.detail.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import team.ppac.analytics.AnalyticsHelper
import team.ppac.search.detail.SearchDetailRoute

const val SEARCH_DETAIL_ROUTE = "search_detail"

fun NavController.navigateToSearchDetail(
    memeCategory: String,
) = navigate("$SEARCH_DETAIL_ROUTE/${memeCategory}")

fun NavGraphBuilder.searchDetailScreen(
    analyticsHelper: AnalyticsHelper,
    navigateBack: () -> Unit,
    navigateToMemeDetail: (String) -> Unit,
) {
    composable(
        route = "$SEARCH_DETAIL_ROUTE/{memeCategory}",
        arguments = listOf(
            navArgument("memeCategory") {
                type = NavType.StringType
            }
        )
    ) {
        SearchDetailRoute(
            analyticsHelper = analyticsHelper,
            navigateBack = navigateBack,
            navigateToMemeDetail = navigateToMemeDetail,
        )
    }
}