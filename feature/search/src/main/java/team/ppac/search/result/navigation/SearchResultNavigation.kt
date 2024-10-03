package team.ppac.search.result.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import team.ppac.search.result.SearchResultRoute
import team.ppac.search.result.mvi.NavigateToMemeDetail

const val SEARCH_RESULT_ROUTE = "search_result"

fun NavController.navigateToSearchResult(
    query: String
) = navigate("$SEARCH_RESULT_ROUTE/${query}")

fun NavGraphBuilder.searchResultScreen(
    navigateBack: () -> Unit,
    navigateToMemeDetail: (String) -> Unit,
) {
    composable(
        route = "$SEARCH_RESULT_ROUTE/{query}",
        arguments = listOf(
            navArgument("query") {
                type = NavType.StringType
            }
        )
    ) {
        SearchResultRoute(
            navigateBack = navigateBack,
            navigateToMemeDetail = navigateToMemeDetail
        )
    }
}