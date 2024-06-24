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
    val large = TextStyleSet(
        bold = pretendardBold(size = 24, lineHeight = 30),
        semibold = pretendardSemiBold(size = 24, lineHeight = 30),
        medium = pretendardMedium(size = 24, lineHeight = 30),
    )
    val medium = TextStyleSet(
        bold = pretendardBold(size = 20, lineHeight = 24),
        semibold = pretendardSemiBold(size = 20, lineHeight = 24),
        medium = pretendardMedium(size = 20, lineHeight = 24),
    )
    val small = TextStyleSet(
        bold = pretendardBold(size = 18, lineHeight = 20),
        semibold = pretendardSemiBold(size = 18, lineHeight = 20),
        medium = pretendardMedium(size = 18, lineHeight = 20),
    )
}

class Body {
    val xLarge = TextStyleSet(
        bold = pretendardBold(size = 16, lineHeight = 20),
        semibold = pretendardSemiBold(size = 16, lineHeight = 20),
        medium = pretendardMedium(size = 16, lineHeight = 20),
    )
    val large = TextStyleSet(
        bold = pretendardBold(size = 15, lineHeight = 18),
        semibold = pretendardSemiBold(size = 15, lineHeight = 18),
        medium = pretendardMedium(size = 15, lineHeight = 18),
    )
    val medium = TextStyleSet(
        bold = pretendardBold(size = 14, lineHeight = 17),
        semibold = pretendardSemiBold(size = 14, lineHeight = 17),
        medium = pretendardMedium(size = 14, lineHeight = 17),
    )
    val small = TextStyleSet(
        bold = pretendardBold(size = 13, lineHeight = 16),
        semibold = pretendardSemiBold(size = 13, lineHeight = 16),
        medium = pretendardMedium(size = 13, lineHeight = 16),
    )
    val xSmall = TextStyleSet(
        bold = pretendardBold(size = 12, lineHeight = 15),
        semibold = pretendardSemiBold(size = 12, lineHeight = 15),
        medium = pretendardMedium(size = 12, lineHeight = 15),
    )
}

class TextStyleSet(
    val bold: TextStyle,
    val semibold: TextStyle,
    val medium: TextStyle,
)

private fun pretendardBold(
    size: Int,
    lineHeight: Int,
) = TextStyle(
    fontFamily = Pretendards,
    fontWeight = FontWeight.Bold,
    fontSize = size.sp,
    lineHeight = lineHeight.sp,
)

private fun pretendardSemiBold(
    size: Int,
    lineHeight: Int,
) = TextStyle(
    fontFamily = Pretendards,
    fontWeight = FontWeight.SemiBold,
    fontSize = size.sp,
    lineHeight = lineHeight.sp,
)

private fun pretendardMedium(
    size: Int,
    lineHeight: Int,
) = TextStyle(
    fontFamily = Pretendards,
    fontWeight = FontWeight.Medium,
    fontSize = size.sp,
    lineHeight = lineHeight.sp,
)