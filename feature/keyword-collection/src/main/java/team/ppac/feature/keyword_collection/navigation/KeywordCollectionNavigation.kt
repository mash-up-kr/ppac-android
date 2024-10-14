package team.ppac.feature.keyword_collection.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import team.ppac.analytics.AnalyticsHelper
import team.ppac.feature.keyword_collection.KeywordCollectionRoute

const val KEYWORD_COLLECTION_ROUTE = "keyword_collection"

fun NavController.navigateToKeywordCollection(
    keyword: String,
) = navigate("$KEYWORD_COLLECTION_ROUTE/${keyword}")

fun NavGraphBuilder.keywordCollectionScreen(
    analyticsHelper: AnalyticsHelper,
    navigateBack: () -> Unit,
    navigateToMemeDetail: (String) -> Unit,
) {
    composable(
        route = "$KEYWORD_COLLECTION_ROUTE/{keyword}",
        arguments = listOf(
            navArgument("keyword") {
                type = NavType.StringType
            }
        )
    ) {
        KeywordCollectionRoute(
            analyticsHelper = analyticsHelper,
            navigateBack = navigateBack,
            navigateToMemeDetail = navigateToMemeDetail,
        )
    }
}