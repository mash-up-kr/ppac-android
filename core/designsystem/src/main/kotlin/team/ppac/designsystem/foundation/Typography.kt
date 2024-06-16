package team.ppac.designsystem.foundation

import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import team.ppac.designsystem.R

internal val Pretendards = FontFamily(
    Font(R.font.pretendard_bold, FontWeight.Bold),
    Font(R.font.pretendard_semibold, FontWeight.SemiBold),
    Font(R.font.pretendard_medium, FontWeight.Medium)
)

internal val LocalFarmemeTypography = staticCompositionLocalOf { FarmemeTypography() }

class FarmemeTypography {
    val heading = Heading()
    val body = Body()
}

class Heading {
    val large = pretendard(
        size = 24,
        fontWeight = FontWeight.Bold,
        lineHeight = 30,
    )
    val medium = pretendard(
        size = 20,
        fontWeight = FontWeight.Bold,
        lineHeight = 24,
    )
    val small = pretendard(
        size = 18,
        fontWeight = FontWeight.Bold,
        lineHeight = 20,
    )
}

class Body {
    val xLarge = pretendard(
        size = 16,
        fontWeight = FontWeight.Bold,
        lineHeight = 20,
    )
    val large = pretendard(
        size = 15,
        fontWeight = FontWeight.Bold,
        lineHeight = 18,
    )
    val medium = pretendard(
        size = 14,
        fontWeight = FontWeight.Bold,
        lineHeight = 17,
    )
    val small = pretendard(
        size = 13,
        fontWeight = FontWeight.Bold,
        lineHeight = 16,
    )
    val xSmall = pretendard(
        size = 12,
        fontWeight = FontWeight.Bold,
        lineHeight = 15,
    )
}

private fun pretendard(
    size: Int,
    fontWeight: FontWeight,
    lineHeight: Int,
) = TextStyle(
    fontFamily = Pretendards,
    fontWeight = fontWeight,
    fontSize = size.sp,
    lineHeight = lineHeight.sp,
)
