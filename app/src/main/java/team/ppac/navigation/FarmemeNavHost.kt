package team.ppac.navigation

import android.app.Activity
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.navOptions
import team.ppac.common.kotlin.model.SnackbarMessage
import team.ppac.mypage.navigation.myPageScreen
import team.ppac.mypage.navigation.navigateToMyPage
import team.ppac.navigator.DetailNavigator
import team.ppac.recommendation.navigation.RECOMMENDATION_ROUTE
import team.ppac.recommendation.navigation.navigateToRecommendation
import team.ppac.recommendation.navigation.recommendationScreen
import team.ppac.search.detail.navigation.navigateToSearchDetail
import team.ppac.search.detail.navigation.searchDetailScreen
import team.ppac.search.main.navigation.navigateToSearch
import team.ppac.search.main.navigation.searchScreen

@Composable
fun FarmemeNavHost(
    modifier: Modifier = Modifier,
    startDestination: String = RECOMMENDATION_ROUTE,
    navController: NavHostController,
    onShowSnackBar: suspend (SnackbarMessage) -> Boolean,
    detailNavigator: DetailNavigator,
) {
    val activity: Activity = LocalContext.current as Activity
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = startDestination,
    ) {
        recommendationScreen()
        searchScreen(
            navigateToSearchDetail = { navController.navigateToSearchDetail(memeCategory = it) }
        )
        searchDetailScreen(
            navigateBack = { navController.navigateUp() }
        )
        myPageScreen(
            navigateToDetail = {
                detailNavigator.navigateFrom(
                    activity = activity,
                )
            }
        )
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
            this@navigateToTopLevelDestination.navigateToMyPage(topLevelNavOptions)
    }

}