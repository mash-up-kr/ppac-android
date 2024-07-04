package team.ppac.mypage.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import team.ppac.designsystem.foundation.FarmemeRadius
import team.ppac.designsystem.util.extension.noRippleClickable

@Composable
internal fun RecentMemeCard(
    modifier: Modifier = Modifier,
    imageUrl: String,
    onClick: () -> Unit,
) {
    Box(
        modifier = modifier
            .size(120.dp)
            .clip(FarmemeRadius.Radius12.shape)
            .noRippleClickable(onClick = onClick),
    ) {
        AsyncImage(
            modifier = Modifier.fillMaxSize(),
            model = ImageRequest.Builder(LocalContext.current)
                .data(imageUrl)
                .crossfade(true)
                .build(),
            contentDescription = null,
            contentScale = ContentScale.Crop,
        )
    }
}

@Preview
@Composable
private fun RecentMemeCardPreview() {
    RecentMemeCard(
        imageUrl = "https://picsum.photos/id/10/2500/1667",
        onClick = { },
    )
}