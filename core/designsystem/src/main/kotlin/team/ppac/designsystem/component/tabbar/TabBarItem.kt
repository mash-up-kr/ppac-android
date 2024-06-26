package team.ppac.designsystem.component.tabbar

import androidx.compose.foundation.layout.RowScope
import androidx.compose.material.BottomNavigationItem
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

// TODO : 디자인 수정 필요
@Composable
fun RowScope.FarmemeNavigationBarItem(
    modifier: Modifier = Modifier,
    isSelected: Boolean,
    enabled: Boolean = true,
    onClick: () -> Unit,
    icon: @Composable () -> Unit,
    selectedIcon: @Composable () -> Unit,
) {
    BottomNavigationItem(
        modifier = modifier,
        selected = isSelected,
        enabled = enabled,
        onClick = onClick,
        icon = if (isSelected) selectedIcon else icon
    )
}