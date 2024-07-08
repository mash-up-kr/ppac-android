package team.ppac.detail.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import team.ppac.designsystem.FarmemeTheme
import team.ppac.designsystem.R
import team.ppac.designsystem.foundation.FarmemeIcon
import team.ppac.designsystem.foundation.FarmemeRadius
import team.ppac.designsystem.util.extension.noRippleClickable
import team.ppac.detail.model.DetailMemeUiModel
import team.ppac.detail.mvi.DetailUiState

@Composable
internal fun DetailContent(
    modifier: Modifier,
    uiModel: DetailMemeUiModel
) {
    Box(
        modifier = modifier
            .border(
                width = 2.dp,
                color = FarmemeTheme.borderColor.primary,
                shape = FarmemeRadius.Radius20.shape,
            ),
    ) {
        Column(
            modifier = Modifier.padding(10.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            AsyncImage(
                model = ImageRequest
                    .Builder(LocalContext.current)
                    .data(uiModel.imageUrl)
                    .crossfade(true)
                    .build(),
                placeholder = painterResource(R.drawable.detail_sample),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier.clip(FarmemeRadius.Radius10.shape),
            )
            DetailTexts(
                name = uiModel.name,
                sourceDescription = uiModel.sourceDescription,
                hashTags = uiModel.hashTags
            )
            DetailFunnyButton()
        }
    }
}

@Composable
internal fun DetailTexts(
    name: String,
    sourceDescription: String,
    hashTags: List<String>,
) {
    Spacer(modifier = Modifier.height(25.dp))
    Text(
        text = name,
        color = FarmemeTheme.textColor.primary,
        style = FarmemeTheme.typography.heading.large.semibold,
    )
    Spacer(modifier = Modifier.height(5.dp))
    DetailTags(hashTags = hashTags)
    Spacer(modifier = Modifier.height(11.dp))
    Text(
        text = "출처: $sourceDescription",
        color = FarmemeTheme.textColor.assistive,
        style = FarmemeTheme.typography.body.xSmall.medium,
    )
    Spacer(modifier = Modifier.height(20.dp))
}

@Composable
internal fun DetailTags(hashTags: List<String>) {
    Row {
        hashTags.forEach { hashTag ->
            Text(
                text = hashTag,
                color = FarmemeTheme.textColor.tertiary,
                style = FarmemeTheme.typography.body.large.medium,
            )
            Spacer(modifier = Modifier.width(6.dp))
        }
    }
}

@Composable
fun DetailFunnyButton() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(46.dp)
            .clip(FarmemeRadius.Radius10.shape)
            .background(color = FarmemeTheme.skeletonColor.primary)
            .noRippleClickable(onClick = {}),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center,
    ) {
        FarmemeIcon.KK()
        Spacer(modifier = Modifier.width(6.dp))
        FarmemeIcon.Funny()
    }
}

@Composable
@Preview(showBackground = true)
fun PreviewDetailContent() {
    DetailContent(
        modifier = Modifier,
        uiModel = DetailUiState.INITIAL_STATE.detailMemeUiModel
    )
}