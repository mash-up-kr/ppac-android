package team.ppac.common.android.component

import android.graphics.Bitmap
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.staggeredgrid.LazyStaggeredGridItemScope
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.graphics.drawable.toBitmap
import coil.compose.AsyncImage
import coil.request.ImageRequest
import team.ppac.common.android.util.copyImageToClipBoard
import team.ppac.designsystem.FarmemeTheme
import team.ppac.designsystem.component.button.FarmemeCircleButton
import team.ppac.designsystem.foundation.FarmemeIcon
import team.ppac.designsystem.foundation.FarmemeRadius
import team.ppac.designsystem.util.extension.noRippleClickable

@Composable
fun LazyStaggeredGridItemScope.FarmemeMemeItem(
    modifier: Modifier = Modifier,
    memeId: String,
    memeTitle: String,
    lolCount: Int,
    imageUrl: String,
    onMemeClick: (String) -> Unit,
    onCopyClick: () -> Unit,
) {
    val bitmap = remember { mutableStateOf<Bitmap?>(null) }
    val context = LocalContext.current

    Column(
        modifier = modifier.noRippleClickable(onClick = { onMemeClick(memeId) }),
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .clip(shape = FarmemeRadius.Radius12.shape)
        ) {
            AsyncImage(
                modifier = Modifier
                    .heightIn(
                        min = FARMEME_MEME_ITEM_MIN_HEIGHT.dp,
                        max = FARMEME_MEME_ITEM_MAX_HEIGHT.dp,
                    )
                    .fillMaxWidth()
                    .background(FarmemeTheme.backgroundColor.black),
                model = ImageRequest.Builder(LocalContext.current)
                    .data(imageUrl)
                    .crossfade(true)
                    .build(),
//                placeholder = painterResource(id = R.drawable.img_sample),  // TODO(JaesungLeee) : API 연결 후 제거 필요
                contentDescription = null,
                contentScale = ContentScale.FillWidth,
                onSuccess = {
                    bitmap.value = it.result.drawable.toBitmap()
                }
            )
            FarmemeCircleButton(
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(end = 10.dp, bottom = 10.dp),
                size = 42.dp,
                backgroundColor = FarmemeTheme.backgroundColor.white,
                onClick = {
                    bitmap.value?.let {
                        onCopyClick()
                        context.copyImageToClipBoard(it)
                    }
                },
                icon = { FarmemeIcon.Copy(Modifier.size(20.dp)) }
            )
        }
        Spacer(modifier = Modifier.size(10.dp))
        Text(
            modifier = Modifier,
            text = memeTitle,
            style = FarmemeTheme.typography.body.medium.medium.copy(
                color = FarmemeTheme.textColor.primary
            ),
            maxLines = 2,
            overflow = TextOverflow.Ellipsis,
        )
        if (lolCount > 0) {
            Spacer(modifier = Modifier.size(8.dp))
            FarmemeLolCount(
                lolCount = lolCount
            )
        }
    }
}

@Composable
fun FarmemeLolCount(
    modifier: Modifier = Modifier,
    lolCount: Int,
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "ㅋㅋ",
            style = FarmemeTheme.typography.highlight.normal.copy(  // TODO(JaesungLeee) : typo style 수정 필요
                color = FarmemeTheme.textColor.tertiary
            )
        )
        Spacer(modifier = Modifier.size(4.dp))
        Text(
            text = "$lolCount",
            style = FarmemeTheme.typography.body.small.medium.copy(
                color = FarmemeTheme.textColor.tertiary
            )
        )
    }
}

@Preview
@Composable
private fun FarmemeLolCountPreview() {
    FarmemeLolCount(lolCount = 10)
}

const val FARMEME_MEME_ITEM_MIN_HEIGHT = 80
const val FARMEME_MEME_ITEM_MAX_HEIGHT = 300