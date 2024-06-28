package team.ppac.navigation

import androidx.annotation.StringRes
import androidx.compose.runtime.Composable
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import team.ppac.designsystem.foundation.FarmemeIcon
import team.ppac.designsystem.R as DesignSystemR

enum class FarmemeTopDestination(
    val selectedIcon: @Composable () -> Unit,
    val unselectedIcon: @Composable () -> Unit,
    @StringRes val textLabelId: Int,
) {
    RECOMMENDATION(
        selectedIcon = { FarmemeIcon.RecommendActive() },
        unselectedIcon = { FarmemeIcon.RecommendInactive() },
        textLabelId = DesignSystemR.string.recommendation_title
    ),
    SEARCH(
        selectedIcon = { FarmemeIcon.DiscoverActive() },
        unselectedIcon = { FarmemeIcon.DiscoverInactive() },
        textLabelId = DesignSystemR.string.search_title
    ),
    MY_PAGE(
        selectedIcon = { FarmemeIcon.MyActive() },
        unselectedIcon = { FarmemeIcon.MyInactive() },
        textLabelId = DesignSystemR.string.mypage_title
    );
}

fun NavDestination?.isTopLevelDestinationInHierarchy(destination: FarmemeTopDestination) =
    this?.hierarchy?.any {
        it.route?.contains(destination.name, true) ?: false
    } ?: false