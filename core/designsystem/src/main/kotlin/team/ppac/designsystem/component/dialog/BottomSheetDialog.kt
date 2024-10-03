package team.ppac.designsystem.component.dialog

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import team.ppac.designsystem.FarmemeTheme
import team.ppac.designsystem.foundation.FarmemeRadius

@Composable
fun FarmemeBottomSheetDialog(
    modifier: Modifier = Modifier,
    canceledOnTouchOutside: Boolean = true,
    isDraggable: Boolean = false,
    bottomSheetProperties: BottomSheetDialogProperties = BottomSheetDialogProperties(
        dismissOnClickOutside = canceledOnTouchOutside,
        dismissOnBackPress = canceledOnTouchOutside,
        navigationBarProperties = NavigationBarProperties(
            color = FarmemeTheme.backgroundColor.white
        ),
        behaviorProperties = BottomSheetBehaviorProperties(
            isDraggable = isDraggable
        )
    ),
    onBottomSheetDismiss: () -> Unit,
    content: @Composable () -> Unit,
) {
    BottomSheetDialog(
        onDismissRequest = onBottomSheetDismiss,
        properties = bottomSheetProperties,
    ) {
        Column(
            modifier = modifier
                .clip(FarmemeRadius.RadiusTop20.shape)
                .fillMaxWidth()
                .background(FarmemeTheme.backgroundColor.white)
                .padding(top = 16.dp, bottom = 10.dp)
        ) {
            content()
        }
    }
}