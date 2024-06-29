package team.ppac.common.android.ui

import androidx.compose.foundation.background
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import team.ppac.designsystem.foundation.FarmemeRadius
import team.ppac.designsystem.util.extension.boxShadow

// TODO(JaesungLeee) : boxShadow Color값 수정 필요.
fun Modifier.shadowButton(
    backGroundColor: Color = Color(0XFFFCFCFC),
    radius: FarmemeRadius = FarmemeRadius.Radius4,
): Modifier = this
    .then(
        boxShadow(
            color = Color(0X3D000000),
            blurRadius = 20.dp,
            offset = DpOffset(x = 0.dp, y = 0.dp)
        )
    )
    .then(background(color = backGroundColor, shape = radius.shape))