package team.ppac.navigation

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.navOptions
import team.ppac.analytics.AnalyticsHelper
import team.ppac.mypage.navigation.myPageScreen
import team.ppac.mypage.navigation.navigateToMyPage
import team.ppac.recommendation.navigation.RECOMMENDATION_ROUTE
import team.ppac.recommendation.navigation.navigateToRecommendation
import team.ppac.recommendation.navigation.recommendationScreen
import team.ppac.feature.keyword_collection.navigation.navigateToKeywordCollection
import team.ppac.feature.keyword_collection.navigation.keywordCollectionScreen
import team.ppac.search.main.navigation.navigateToSearch
import team.ppac.search.main.navigation.searchScreen
import team.ppac.search.result.navigation.navigateToSearchResult
import team.ppac.search.result.navigation.searchResultScreen

@Composable
fun FarmemeNavHost(
    modifier: Modifier = Modifier,
    analyticsHelper: AnalyticsHelper,
    startDestination: String = RECOMMENDATION_ROUTE,
    navController: NavHostController,
    navigateToDetail: (String) -> Unit,
    navigateToSetting: () -> Unit,
    navigateToRegister: () -> Unit,
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = startDestination,
        enterTransition = { EnterTransition.None },
        exitTransition = { ExitTransition.None },
    ) {
        recommendationScreen(
            analyticsHelper = analyticsHelper,
            navigateToRegister = navigateToRegister,
        )
        searchScreen(
            analyticsHelper = analyticsHelper,
            navigateToKeywordCollection = { navController.navigateToKeywordCollection(it) },
            navigateToSearchResult = { navController.navigateToSearchResult(it) }
        )
        keywordCollectionScreen(
            analyticsHelper = analyticsHelper,
            navigateBack = { navController.popBackStack() },
            navigateToMemeDetail = navigateToDetail
        )
        searchResultScreen(
            navigateBack = { navController.popBackStack() },
            navigateToMemeDetail = navigateToDetail
        )
        myPageScreen(
            analyticsHelper = analyticsHelper,
            navigateToDetail = navigateToDetail,
            navigateToSetting = navigateToSetting,
            navigateToRegister = navigateToRegister,
        )
    }
}

fun NavHostController.navigateToTopLevelDestination(topLevelDestination: FarmemeTopDestination) {
    val topLevelNavOptions = navOptions {
        popUpTo(this@navigateToTopLevelDestination.graph.findStartDestination().id) {
            saveState = false
        }
        launchSingleTop = true
        restoreState = false
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