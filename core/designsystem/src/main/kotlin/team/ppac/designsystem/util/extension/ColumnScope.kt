package team.ppac.designsystem.util.extension

import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.heightIn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp


@Composable
fun ColumnScope.ColumnSpacerByWeight(
    weight: Float,
    isFilled: Boolean = true
) {
    Spacer(modifier = Modifier.weight(weight, isFilled))
}

@Composable
fun ColumnScope.ColumnSpacerByWeightWithMinHeight(
    weight: Float,
    minHeight: Dp,
    isFilled: Boolean = true,
) {
    Spacer(modifier = Modifier.weight(weight = weight, fill = isFilled))
    Spacer(modifier = Modifier.heightIn(min = minHeight))
}