package team.ppac.search.main.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import team.ppac.analytics.AnalyticsHelper
import team.ppac.search.main.SearchRoute

const val SEARCH_ROUTE = "search"

fun NavController.navigateToSearch(navOptions: NavOptions) = navigate(SEARCH_ROUTE, navOptions)

fun NavGraphBuilder.searchScreen(
    analyticsHelper: AnalyticsHelper,
    navigateToSearchDetail: (String) -> Unit,
    navigateToSearchResult: (String) -> Unit,
) {
    composable(
        route = SEARCH_ROUTE
    ) {
        SearchRoute(
            analyticsHelper = analyticsHelper,
            navigateToSearchDetail = navigateToSearchDetail,
            navigateToSearchResult = navigateToSearchResult,
        )
    }
}