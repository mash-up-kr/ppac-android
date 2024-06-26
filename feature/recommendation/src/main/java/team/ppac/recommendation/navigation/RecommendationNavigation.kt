package team.ppac.recommendation.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import team.ppac.recommendation.RecommendationRoute

const val RECOMMENDATION_ROUTE = "recommendation"

fun NavGraphBuilder.recommendationScreen() {
    composable(
        route = RECOMMENDATION_ROUTE
    ) {
        RecommendationRoute()
    }
}