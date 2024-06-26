package team.ppac.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.navOptions
import team.ppac.mypage.navigation.myPageScreen
import team.ppac.mypage.navigation.navigateToMyPage
import team.ppac.recommendation.navigation.RECOMMENDATION_ROUTE
import team.ppac.recommendation.navigation.navigateToRecommendation
import team.ppac.recommendation.navigation.recommendationScreen
import team.ppac.search.navigation.navigateToSearch
import team.ppac.search.navigation.searchScreen

@Composable
fun FarmemeNavHost(
    modifier: Modifier = Modifier,
    startDestination: String = RECOMMENDATION_ROUTE,
    navController: NavHostController,
    onShowSnackBar: suspend (String, String?) -> Boolean,
) {
    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier,
    ) {
        recommendationScreen()
        searchScreen()
        myPageScreen()
    }
}

fun NavHostController.navigateToTopLevelDestination(topLevelDestination: FarmemeTopDestination) {
    val topLevelNavOptions = navOptions {
        popUpTo(this@navigateToTopLevelDestination.graph.findStartDestination().id) {
            saveState = true
        }
        launchSingleTop = true
        restoreState = true
    }

    when (topLevelDestination) {
        FarmemeTopDestination.RECOMMENDATION ->
            this@navigateToTopLevelDestination.navigateToRecommendation(topLevelNavOptions)

        FarmemeTopDestination.SEARCH ->
            this@navigateToTopLevelDestination.navigateToSearch(topLevelNavOptions)

        FarmemeTopDestination.MY_PAGE ->
            this@navigateToTopLevelDestination.navigateToMyPage(topLevelNavOptions      )
    }

}