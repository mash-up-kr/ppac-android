package team.ppac.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import team.ppac.mypage.navigation.myPageScreen
import team.ppac.recommendation.navigation.RECOMMENDATION_ROUTE
import team.ppac.recommendation.navigation.recommendationScreen
import team.ppac.search.navigation.searchScreen

@Composable
fun FarmemeNavHost(
    onShowSnackBar: suspend (String, String?) -> Boolean,
    modifier: Modifier = Modifier,
    startDestination: String = RECOMMENDATION_ROUTE,
) {
    val navController = rememberNavController()
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