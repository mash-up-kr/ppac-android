package team.ppac.designsystem.foundation

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.unit.dp

enum class FarmemeRadius(val shape: RoundedCornerShape) {
    Radius4(RoundedCornerShape(size = 4.dp)),
    Radius10(RoundedCornerShape(size = 10.dp)),
    Radius12(RoundedCornerShape(size = 12.dp)),
    Radius20(RoundedCornerShape(size = 20.dp)),
    Radius25(RoundedCornerShape(size = 25.dp)),
    Radius30(RoundedCornerShape(size = 30.dp)),
    Radius35(RoundedCornerShape(size = 35.dp)),
    Radius40(RoundedCornerShape(size = 40.dp)),
    RadiusTop30(RoundedCornerShape(topStart = 30.dp, topEnd = 30.dp)),
    RadiusTop20(RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp)),
    RadiusBottom30(RoundedCornerShape(bottomStart = 30.dp, bottomEnd = 30.dp)),
}