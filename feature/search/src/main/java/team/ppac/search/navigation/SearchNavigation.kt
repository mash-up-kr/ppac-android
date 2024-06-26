package team.ppac.search.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import team.ppac.search.SearchRoute

const val SEARCH_ROUTE = "search"

fun NavGraphBuilder.searchScreen() {
    composable(
        route = SEARCH_ROUTE
    ) {
        SearchRoute()
    }
}