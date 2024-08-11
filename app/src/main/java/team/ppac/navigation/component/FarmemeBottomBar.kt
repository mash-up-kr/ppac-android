package team.ppac.navigation.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import team.ppac.designsystem.FarmemeTheme
import team.ppac.designsystem.component.scaffold.FarmemeScaffold
import team.ppac.designsystem.component.tabbar.FarmemeNavigationBar
import team.ppac.designsystem.component.tabbar.FarmemeNavigationBarItem
import team.ppac.navigation.FarmemeTopDestination
import team.ppac.navigation.isTopLevelDestinationInHierarchy

@Composable
fun FarmemeBottomBar(
    modifier: Modifier = Modifier,
    destinations: List<FarmemeTopDestination> = FarmemeTopDestination.entries,
    navigateToDestination: (FarmemeTopDestination) -> Unit,
    currentDestination: NavDestination?,
) {
    FarmemeNavigationBar(
        modifier = modifier.fillMaxWidth(),
    ) {
        destinations.forEach { destination ->
            val isSelected = currentDestination.isTopLevelDestinationInHierarchy(destination)

            FarmemeNavigationBarItem(
                modifier = Modifier,
                isSelected = isSelected,
                onClick = { navigateToDestination(destination) },
                label = {
                    Text(
                        text = stringResource(id = destination.textLabelId),
                        style = FarmemeTheme.typography.body.xSmall.semibold
                    )
                },
                selectedIcon = destination.selectedIcon,
                unselectedIcon = destination.unselectedIcon
            )
        }
    }
}

@Preview
@Composable
private fun FarmemeBottomBarPreview() {
    val navController = rememberNavController()
    FarmemeScaffold(
        bottomBar = {
            FarmemeBottomBar(
                navigateToDestination = {},
                currentDestination = navController.currentBackStackEntryAsState().value?.destination
            )
        },
    ) {

    }
}
