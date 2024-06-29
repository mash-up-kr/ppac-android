package team.ppac.search.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import team.ppac.designsystem.FarmemeTheme
import team.ppac.designsystem.R
import team.ppac.designsystem.foundation.FarmemeRadius
import team.ppac.search.model.HotKeywordUiModel
import team.ppac.search.preview.HotKeywordCardProvider

@Composable
fun HotKeywordCard(
    modifier: Modifier = Modifier,
    imageUrl: String?,
    description: String,
) {
    Box(
        modifier = modifier
            .size(90.dp)
            .clip(FarmemeRadius.Radius12.shape)
            .border(
                width = 2.dp,
                color = FarmemeTheme.borderColor.primary,
                shape = FarmemeRadius.Radius12.shape,
            ),
        contentAlignment = Alignment.Center
    ) {
        AsyncImage(
            modifier = Modifier.fillMaxSize(),
            model = ImageRequest.Builder(LocalContext.current)
                .data(imageUrl)
                .crossfade(true)
                .build(),
            placeholder = painterResource(id = R.drawable.img_sample),  // TODO(JaesungLeee) : API 연결 후 제거 필요
            contentDescription = null,
            contentScale = ContentScale.Crop
        )
        Text(
            modifier = Modifier.padding(horizontal = 16.dp),
            text = description,
            style = FarmemeTheme.typography.body.large.semibold.copy(
                color = FarmemeTheme.textColor.inverse
            ),
            maxLines = 2,
            overflow = TextOverflow.Ellipsis,
        )

    }
}

@Preview
@Composable
private fun HotKeywordCardPreview(
    @PreviewParameter(provider = HotKeywordCardProvider::class) card: HotKeywordUiModel
) {
    Box(
        modifier = Modifier.background(Color.White)
    ) {
        HotKeywordCard(
            imageUrl = card.imageUrl,
            description = card.description
        )
    }
}
