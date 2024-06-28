package team.ppac.designsystem.component.tabbar

import androidx.compose.foundation.layout.RowScope
import androidx.compose.material.BottomNavigationItem
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

// TODO(JaesungLeee) : 디자인 수정 필요
@Composable
fun RowScope.FarmemeNavigationBarItem(
    modifier: Modifier = Modifier,
    isSelected: Boolean,
    enabled: Boolean = true,
    onClick: () -> Unit,
    selectedIcon: @Composable () -> Unit,
    unselectedIcon: @Composable () -> Unit,
) {
    BottomNavigationItem(
        modifier = modifier,
        selected = isSelected,
        enabled = enabled,
        onClick = onClick,
        icon = if (isSelected) selectedIcon else unselectedIcon
    )
}