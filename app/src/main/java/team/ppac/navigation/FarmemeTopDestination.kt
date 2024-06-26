package team.ppac.navigation

import team.ppac.R
import team.ppac.designsystem.R as DesignSystemR

enum class FarmemeTopDestination(
    val selectedIcon: Int,
    val unselectedIcon: Int,
    val iconTextId: Int,
    val textLabelId: Int,
) {
    RECOMMENDATION(
        selectedIcon = R.drawable.ic_launcher_foreground,
        unselectedIcon = R.drawable.ic_launcher_foreground,
        iconTextId = DesignSystemR.string.recommendation_title,
        textLabelId = DesignSystemR.string.recommendation_title,
    ),
    SEARCH(
        selectedIcon = R.drawable.ic_launcher_foreground,
        unselectedIcon = R.drawable.ic_launcher_foreground,
        iconTextId = DesignSystemR.string.recommendation_title,
        textLabelId = DesignSystemR.string.recommendation_title,
    ),
    MY_PAGE(
        selectedIcon = R.drawable.ic_launcher_foreground,
        unselectedIcon = R.drawable.ic_launcher_foreground,
        iconTextId = DesignSystemR.string.recommendation_title,
        textLabelId = DesignSystemR.string.recommendation_title,
    );
}
