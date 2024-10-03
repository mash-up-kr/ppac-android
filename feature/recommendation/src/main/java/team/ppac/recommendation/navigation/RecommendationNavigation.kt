package team.ppac.recommendation.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import team.ppac.analytics.AnalyticsHelper
import team.ppac.recommendation.RecommendationRoute

const val RECOMMENDATION_ROUTE = "recommendation"

fun NavController.navigateToRecommendation(navOptions: NavOptions) = navigate(RECOMMENDATION_ROUTE, navOptions)

fun NavGraphBuilder.recommendationScreen(
    analyticsHelper: AnalyticsHelper,
    navigateToRegister: () -> Unit
) {
    composable(
        route = RECOMMENDATION_ROUTE
    ) {
        RecommendationRoute(
            analyticsHelper = analyticsHelper,
            navigateToRegister = navigateToRegister,
        )
    }
}