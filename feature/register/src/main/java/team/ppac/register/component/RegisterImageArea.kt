package team.ppac.register.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import team.ppac.designsystem.FarmemeTheme
import team.ppac.designsystem.component.button.FarmemeCircleButton
import team.ppac.designsystem.component.button.FarmemeWeakButton
import team.ppac.designsystem.foundation.FarmemeIcon
import team.ppac.designsystem.foundation.FarmemeRadius

@Composable
internal fun RegisterImageArea(
    modifier: Modifier = Modifier,
    hasImage: Boolean,
    loadImage: () -> Unit = {},
) {
    val borderColor =
        if (hasImage) FarmemeTheme.borderColor.primary else FarmemeTheme.borderColor.tertiary

    Box(
        modifier = modifier
            .padding(vertical = 48.dp)
            .width(206.dp)
            .height(234.dp)
            .clip(FarmemeRadius.Radius20.shape)
            .border(
                width = 2.dp,
                color = borderColor,
                shape = FarmemeRadius.Radius20.shape,
            )
            .background(FarmemeTheme.backgroundColor.assistive),
        contentAlignment = Alignment.Center,
    ) {
        if (!hasImage) {
            FarmemeWeakButton(
                text = "이미지 등록",
                withStar = true,
                onClick = loadImage,
                icon = {
                    FarmemeIcon.Image(
                        modifier = Modifier.size(20.dp),
                    )
                }
            )
        } else {
            // AsyncImage
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(FarmemeTheme.backgroundColor.brandAssistive),
            )
            FarmemeCircleButton(
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(12.dp),
                backgroundColor = FarmemeTheme.backgroundColor.white,
                onClick = loadImage,
                icon = { FarmemeIcon.Image(modifier = Modifier.size(20.dp)) },
            )
        }
    }
}

@Preview
@Composable
internal fun RegisterImageAreaPreview() {
    Column {
        RegisterImageArea(
            hasImage = true,
        )
        RegisterImageArea(
            hasImage = false,
        )
    }
}