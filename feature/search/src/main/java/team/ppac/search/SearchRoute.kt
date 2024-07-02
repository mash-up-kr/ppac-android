package team.ppac.search

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
internal fun SearchRoute(
    modifier: Modifier = Modifier,
) {
    SearchScreen(
        modifier = modifier,
        onCategoryClick = {},
        navigateToSearchDetail = {}
    )
}