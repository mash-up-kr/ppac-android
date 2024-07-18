package team.ppac.detail.component

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.Text
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import team.ppac.common.android.util.shareOneLink
import team.ppac.designsystem.FarmemeTheme
import team.ppac.designsystem.component.tabbar.TabBar
import team.ppac.designsystem.foundation.FarmemeIcon
import team.ppac.designsystem.foundation.FarmemeRadius

@Composable
internal fun DetailBottomBar(
    memeId: String,
    isSaved: Boolean,
    copyBitmap: () -> Unit,
    onClickFarmemeButton: (String, Boolean) -> Unit,
) {
    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()

    val originalColor = FarmemeTheme.textColor.primary
    val selectedColor = FarmemeTheme.textColor.brand

    var copyButtonColor by remember { mutableStateOf(originalColor) }

    val farmemeButtonColor = if (isSaved) selectedColor else originalColor

    val animatedCopyButtonColor by animateColorAsState(targetValue = copyButtonColor)
    val animatedFarmemeButtonColor by animateColorAsState(targetValue = farmemeButtonColor)

    TabBar(modifier = Modifier.fillMaxWidth()) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.CenterVertically)
                .padding(horizontal = 20.dp),
        ) {
            DetailBottomButton(
                title = "복사",
                textColor = animatedCopyButtonColor,
                onClickButton = {
                    copyButtonColor = selectedColor
                    coroutineScope.launch {
                        delay(2000)
                        copyButtonColor = originalColor
                    }
                    copyBitmap()
                },
            ) {
                FarmemeIcon.Copy(
                    modifier = Modifier.size(20.dp),
                    tint = animatedCopyButtonColor,
                )
            }
            DetailBottomButton(
                title = "공유",
                onClickButton = { context.shareOneLink(memeId) },
            ) {
                FarmemeIcon.Share(modifier = Modifier.size(20.dp))
            }
            DetailBottomButton(
                title = "파밈",
                textColor = animatedFarmemeButtonColor,
                onClickButton = {
                    onClickFarmemeButton(memeId, isSaved)
                },
            ) {
                FarmemeIcon.BookmarkLine(
                    modifier = Modifier.size(20.dp),
                    tint = animatedFarmemeButtonColor,
                )
            }
        }
    }
}

@Composable
internal fun RowScope.DetailBottomButton(
    title: String,
    textColor: Color = FarmemeTheme.textColor.primary,
    onClickButton: () -> Unit,
    icon: @Composable () -> Unit,
) {
    Row(
        modifier = Modifier
            .weight(1f)
            .clip(shape = FarmemeRadius.Radius10.shape)
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = rememberRipple(color = FarmemeTheme.skeletonColor.primary),
                onClick = { onClickButton() },
            )
            .padding(vertical = 15.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center,
    ) {
        icon()
        Spacer(modifier = Modifier.width(4.dp))
        Text(
            text = title,
            style = FarmemeTheme.typography.body.xLarge.semibold,
            color = textColor,
        )
    }
}

@Composable
@Preview(showBackground = true)
fun PreviewDetailBottomBar() {
    DetailBottomBar(
        memeId = "",
        isSaved = false,
        copyBitmap = {},
        onClickFarmemeButton = { _, _ -> },
    )
}