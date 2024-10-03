package team.ppac.designsystem.foundation

import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import team.ppac.designsystem.FarmemeTheme
import team.ppac.designsystem.R

object FarmemeIcon {
    @Composable
    fun ArrowRight(
        modifier: Modifier = Modifier,
        tint: Color = Color.Unspecified,
    ) = Icon(
        modifier = modifier,
        painter = painterResource(R.drawable.ic_arrow_right_24),
        contentDescription = null,
        tint = tint,
    )

    @Composable
    fun Back(
        modifier: Modifier = Modifier,
        tint: Color = Color.Unspecified,
    ) = Icon(
        modifier = modifier,
        painter = painterResource(R.drawable.ic_back_24),
        contentDescription = null,
        tint = tint,
    )

    @Composable
    fun BookmarkFilled(
        modifier: Modifier = Modifier,
    ) = Icon(
        modifier = modifier,
        painter = painterResource(R.drawable.ic_bookmark_filled_24),
        contentDescription = null,
        tint = Color.Unspecified,
    )

    @Composable
    fun BookmarkLine(
        modifier: Modifier = Modifier,
        tint: Color = Color.Unspecified,
    ) = Icon(
        modifier = modifier,
        painter = painterResource(R.drawable.ic_bookmark_line_24),
        contentDescription = null,
        tint = tint,
    )

    @Composable
    fun Category(
        modifier: Modifier = Modifier,
        tint: Color = Color.Unspecified,
    ) = Icon(
        modifier = modifier,
        painter = painterResource(R.drawable.ic_category_24),
        contentDescription = null,
        tint = tint,
    )

    @Composable
    fun Check(
        modifier: Modifier = Modifier,
        tint: Color = Color.Unspecified,
    ) = Icon(
        modifier = modifier,
        painter = painterResource(R.drawable.ic_check_24),
        contentDescription = null,
        tint = tint,
    )

    @Composable
    fun SuccessFilled(
        modifier: Modifier = Modifier,
        tint: Color = Color.Unspecified,
    ) = Icon(
        modifier = modifier,
        painter = painterResource(R.drawable.ic_success_filled_24),
        contentDescription = null,
        tint = tint,
    )

    @Composable
    fun SuccessFilledBrand(
        modifier: Modifier = Modifier,
    ) = Icon(
        modifier = modifier,
        painter = painterResource(R.drawable.ic_success_filled_brand_24),
        contentDescription = null,
        tint = Color.Unspecified,
    )

    @Composable
    fun SuccessOutlined(
        modifier: Modifier = Modifier,
        tint: Color = Color.Unspecified,
    ) = Icon(
        modifier = modifier,
        painter = painterResource(R.drawable.ic_success_outlined_24),
        contentDescription = null,
        tint = tint,
    )

    @Composable
    fun Copy(
        modifier: Modifier = Modifier,
        tint: Color = Color.Unspecified,
    ) = Icon(
        modifier = modifier,
        painter = painterResource(R.drawable.ic_copy_24),
        contentDescription = null,
        tint = tint,
    )

    @Composable
    fun CopyFilled(
        modifier: Modifier = Modifier,
        tint: Color = Color.Unspecified,
    ) = Icon(
        modifier = modifier,
        painter = painterResource(R.drawable.ic_copy_fill_20),
        contentDescription = null,
        tint = tint
    )

    @Composable
    fun Delete(
        modifier: Modifier = Modifier,
        tint: Color = Color.Unspecified,
    ) = Icon(
        modifier = modifier,
        painter = painterResource(R.drawable.ic_delete_24),
        contentDescription = null,
        tint = tint,
    )

    @Composable
    fun Level1(
        modifier: Modifier = Modifier,
    ) = Icon(
        modifier = modifier,
        painter = painterResource(R.drawable.ic_level_1_24),
        contentDescription = null,
        tint = Color.Unspecified,
    )

    @Composable
    fun Level2(
        modifier: Modifier = Modifier,
    ) = Icon(
        modifier = modifier,
        painter = painterResource(R.drawable.ic_level_2_24),
        contentDescription = null,
        tint = Color.Unspecified,
    )

    @Composable
    fun Level3(
        modifier: Modifier = Modifier,
    ) = Icon(
        modifier = modifier,
        painter = painterResource(R.drawable.ic_level_3_24),
        contentDescription = null,
        tint = Color.Unspecified,
    )

    @Composable
    fun Level4(
        modifier: Modifier = Modifier,
    ) = Icon(
        modifier = modifier,
        painter = painterResource(R.drawable.ic_level_4_24),
        contentDescription = null,
        tint = Color.Unspecified,
    )

    @Composable
    fun LevelCheck(
        modifier: Modifier = Modifier,
    ) = Icon(
        modifier = modifier,
        painter = painterResource(R.drawable.ic_level_check_24),
        contentDescription = null,
        tint = Color.Unspecified,
    )

    @Composable
    fun LevelDisabled(
        modifier: Modifier = Modifier,
    ) = Icon(
        modifier = modifier,
        painter = painterResource(R.drawable.ic_level_disabled_24),
        contentDescription = null,
        tint = Color.Unspecified,
    )

    @Composable
    fun MyActive(
        modifier: Modifier = Modifier,
    ) = Icon(
        modifier = modifier,
        painter = painterResource(R.drawable.ic_my_active_24),
        contentDescription = null,
        tint = Color.Unspecified,
    )

    @Composable
    fun MyInactive(
        modifier: Modifier = Modifier,
    ) = Icon(
        modifier = modifier,
        painter = painterResource(R.drawable.ic_my_inactive_24),
        contentDescription = null,
        tint = FarmemeTheme.iconColor.assistive,
    )

    @Composable
    fun RecommendActive(
        modifier: Modifier = Modifier,
    ) = Icon(
        modifier = modifier,
        painter = painterResource(R.drawable.ic_recommend_active_24),
        contentDescription = null,
        tint = Color.Unspecified,
    )

    @Composable
    fun RecommendInactive(
        modifier: Modifier = Modifier,
    ) = Icon(
        modifier = modifier,
        painter = painterResource(R.drawable.ic_recommend_inactive_24),
        contentDescription = null,
        tint = FarmemeTheme.iconColor.assistive,
    )

    @Composable
    fun Search(
        modifier: Modifier = Modifier,
        tint: Color = Color.Unspecified,
    ) = Icon(
        modifier = modifier,
        painter = painterResource(R.drawable.ic_search_24),
        contentDescription = null,
        tint = tint,
    )

    @Composable
    fun DiscoverActive(
        modifier: Modifier = Modifier,
        tint: Color = Color.Unspecified,
    ) = Icon(
        modifier = modifier,
        painter = painterResource(R.drawable.ic_discover_active_24),
        contentDescription = null,
        tint = tint,
    )

    @Composable
    fun DiscoverInactive(
        modifier: Modifier = Modifier,
        tint: Color = Color.Unspecified,
    ) = Icon(
        modifier = modifier,
        painter = painterResource(R.drawable.ic_discover_inactive_24),
        contentDescription = null,
        tint = FarmemeTheme.iconColor.assistive,
    )

    @Composable
    fun Setting(
        modifier: Modifier = Modifier,
    ) = Icon(
        modifier = modifier,
        painter = painterResource(R.drawable.ic_setting_24),
        contentDescription = null,
        tint = Color.Unspecified,
    )

    @Composable
    fun Share(
        modifier: Modifier = Modifier,
        tint: Color = Color.Unspecified,
    ) = Icon(
        modifier = modifier,
        painter = painterResource(R.drawable.ic_share_24),
        contentDescription = null,
        tint = tint,
    )

    @Composable
    fun Special(
        modifier: Modifier = Modifier,
        tint: Color = Color.Unspecified,
    ) = Icon(
        modifier = modifier,
        painter = painterResource(R.drawable.ic_special_24),
        contentDescription = null,
        tint = tint,
    )

    @Composable
    fun Funny(
        modifier: Modifier = Modifier,
        tint: Color = Color.Unspecified,
    ) = Icon(
        modifier = modifier,
        painter = painterResource(id = R.drawable.ic_funny),
        contentDescription = null,
        tint = tint,
    )

    @Composable
    fun KK(
        modifier: Modifier = Modifier,
        tint: Color = Color.Unspecified,
    ) = Icon(
        modifier = modifier,
        painter = painterResource(id = R.drawable.ic_kk),
        contentDescription = null,
        tint = tint,
    )

    @Composable
    fun KKHorizon(
        modifier: Modifier = Modifier,
        tint: Color = Color.Unspecified,
    ) = Icon(
        modifier = modifier,
        painter = painterResource(id = R.drawable.ic_kk_horizon),
        contentDescription = null,
        tint = tint,
    )

    @Composable
    fun KKMeme(
        modifier: Modifier = Modifier,
        tint: Color = Color.Unspecified,
    ) = Icon(
        modifier = modifier,
        painter = painterResource(id = R.drawable.ic_kk_memelist),
        contentDescription = null,
        tint = tint,
    )

    @Composable
    fun CheckRectangle(
        modifier: Modifier = Modifier,
        tint: Color = Color.Unspecified,
    ) = Icon(
        modifier = modifier,
        painter = painterResource(R.drawable.ic_check_rectangle_24),
        contentDescription = null,
        tint = tint,
    )

    @Composable
    fun SoFunny(
        modifier: Modifier = Modifier,
        tint: Color = Color.Unspecified,
    ) = Icon(
        modifier = modifier,
        painter = painterResource(R.drawable.ic_so_funny),
        contentDescription = null,
        tint = tint,
    )

    @Composable
    fun Lol(
        modifier: Modifier = Modifier,
        tint: Color = Color.Unspecified,
    ) = Icon(
        modifier = modifier,
        painter = painterResource(R.drawable.ic_lol),
        contentDescription = null,
        tint = tint,
    )

    @Composable
    fun Stroke(
        modifier: Modifier = Modifier,
        tint: Color = Color.Unspecified,
    ) = Icon(
        modifier = modifier,
        painter = painterResource(R.drawable.ic_stroke_24),
        contentDescription = null,
        tint = tint,
    )

    @Composable
    fun Caution(
        modifier: Modifier = Modifier,
    ) = Icon(
        modifier = modifier,
        painter = painterResource(R.drawable.ic_caution_48),
        contentDescription = null,
    )

    @Composable
    fun Required(
        modifier: Modifier = Modifier,
    ) = Icon(
        modifier = modifier,
        painter = painterResource(R.drawable.ic_required),
        contentDescription = null,
        tint = Color.Unspecified
    )

    @Composable
    fun Media(
        modifier: Modifier = Modifier,
    ) = Icon(
        modifier = modifier,
        painter = painterResource(R.drawable.ic_media_24),
        contentDescription = null,
        tint = Color.Unspecified
    )

    @Composable
    fun Upload(
        modifier: Modifier = Modifier,
    ) = Icon(
        modifier = modifier,
        painter = painterResource(R.drawable.ic_upload_16),
        contentDescription = null,
        tint = Color.Unspecified
    )

    @Composable
    fun StrokeDot(
        modifier: Modifier = Modifier,
    ) = Icon(
        modifier = modifier,
        painter = painterResource(id = R.drawable.ic_stroke),
        contentDescription = null,
        tint = Color.Unspecified
    )
}