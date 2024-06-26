package team.ppac.navigation

import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import team.ppac.R
import team.ppac.designsystem.R as DesignSystemR

enum class FarmemeTopDestination(
    val selectedIcon: Int,
    val unselectedIcon: Int,
    val iconTextId: Int,
    val textLabelId: Int,
) {
    RECOMMENDATION(
        selectedIcon = DesignSystemR.drawable.ic_recommend_active_24,
        unselectedIcon = DesignSystemR.drawable.ic_recommend_inactive_24,
        iconTextId = DesignSystemR.string.recommendation_title,
        textLabelId = DesignSystemR.string.recommendation_title,
    ),
    SEARCH(
        selectedIcon = DesignSystemR.drawable.ic_search_active_24,
        unselectedIcon = R.drawable.ic_launcher_foreground,
        iconTextId = DesignSystemR.string.recommendation_title,
        textLabelId = DesignSystemR.string.recommendation_title,
    ),
    MY_PAGE(
        selectedIcon = DesignSystemR.drawable.ic_my_active_24,
        unselectedIcon = DesignSystemR.drawable.ic_my_inactive_24,
        iconTextId = DesignSystemR.string.recommendation_title,
        textLabelId = DesignSystemR.string.recommendation_title,
    );
}

fun NavDestination?.isTopLevelDestinationInHierarchy(destination: FarmemeTopDestination) =
    this?.hierarchy?.any {
        it.route?.contains(destination.name, true) ?: false
    } ?: false