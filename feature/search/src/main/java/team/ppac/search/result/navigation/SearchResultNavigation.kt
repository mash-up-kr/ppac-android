package team.ppac.search.result.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import team.ppac.search.result.SearchResultRoute

const val SEARCH_RESULT_ROUTE = "search_result"

fun NavController.navigateToSearchResult() = navigate(SEARCH_RESULT_ROUTE)

fun NavGraphBuilder.searchResultScreen(
    navigateBack: () -> Unit,
) {
    composable(
        route = SEARCH_RESULT_ROUTE
    ) {
        SearchResultRoute()
    }
}