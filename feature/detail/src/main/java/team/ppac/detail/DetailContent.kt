package team.ppac.detail

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import team.ppac.designsystem.component.tabbar.TabBar
import team.ppac.designsystem.foundation.FarmemeIcon
import team.ppac.designsystem.foundation.FarmemeRadius

@Composable
fun DetailContent() {
    Box(
        modifier = Modifier
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
                    .data(TEST_IMAGE_PREVIEW)
                    .crossfade(true)
                    .build(),
                placeholder = painterResource(R.drawable.detail_sample),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier.clip(FarmemeRadius.Radius10.shape),
            )
            DetailTexts()
            DetailFunnyButton()
        }
    }
}

@Composable
fun DetailTexts() {
    Spacer(modifier = Modifier.height(25.dp))
    Text(
        text = "나는 공부를 찢어",
        color = FarmemeTheme.textColor.primary,
        style = FarmemeTheme.typography.heading.large.semibold,
    )
    Spacer(modifier = Modifier.height(5.dp))
    DetailTags()
    Spacer(modifier = Modifier.height(11.dp))
    Text(
        text = "출처: 출처에 대한 내용이 들어갑니다.",
        color = FarmemeTheme.textColor.assistive,
        style = FarmemeTheme.typography.body.xSmall.medium,
    )
    Spacer(modifier = Modifier.height(20.dp))
}

@Composable
fun DetailTags(tags: List<String> = listOf("#공부", "#학생", "#시험기간", "힘듦", "피곤")) {
    Row {
        tags.forEach { tag ->
            Text(
                text = tag,
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
            .clickable { },
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center,
    ) {
        FarmemeIcon.KK()
        Spacer(modifier = Modifier.width(6.dp))
        FarmemeIcon.Funny()
    }
}

@Composable
fun DetailBottomBar() {
    TabBar(modifier = Modifier.fillMaxWidth()) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.CenterVertically)
                .padding(horizontal = 20.dp),
        ) {
            DetailBottomButton(
                icon = { FarmemeIcon.Copy(modifier = Modifier.size(20.dp)) },
                title = "복사",
                onClickButton = {}
            )
            DetailBottomButton(
                icon = { FarmemeIcon.Share(modifier = Modifier.size(20.dp)) },
                title = "공유",
                onClickButton = {}
            )
            DetailBottomButton(
                icon = { FarmemeIcon.BookmarkLine(modifier = Modifier.size(20.dp)) },
                title = "북마크",
                onClickButton = {}
            )
        }
    }
}

@Composable
fun RowScope.DetailBottomButton(
    icon: @Composable () -> Unit,
    title: String,
    onClickButton: () -> Unit,
) {
    Row(
        modifier = Modifier
            .weight(1f)
            .clickable(onClick = onClickButton)
            .padding(vertical = 15.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center,
    ) {
        icon()
        Spacer(modifier = Modifier.width(4.dp))
        Text(
            text = title,
            style = FarmemeTheme.typography.body.xLarge.semibold,
            color = FarmemeTheme.textColor.primary,
        )
    }
}

@Composable
@Preview(showBackground = true)
fun PreviewDetailContent() {
    DetailContent()
}

@Composable
@Preview(showBackground = true)
fun PreviewDetailBottomBar() {
    DetailBottomBar()
}

private const val TEST_IMAGE_PREVIEW =
    "https://s3-alpha-sig.figma.com/img/2215/ecea/fcea7f8dbec74e16f56675f756edb8b5?Expires=1720396800&Key-Pair-Id=APKAQ4GOSFWCVNEHN3O4&Signature=P8TH9OlXy0-7cZDofJLFdsRUNDhXsiEaeRgPKZSvHAQM8jNRMRulpsz7s1cvBuQWRvoB1vfYpY1eGZdRyPGgZEWafyvNXDh9GxJS2n~PBNgGSxGy10c2uRU1OmOxE5hwMlci6BGGvLamKTO1LZ4A4yPEaCwPtVyZswWFbAXYdCSUMidW0zp94EDCCCcFPmOp0Un6usY7AsZ18bchDMY-iQmqSG9V8dqyQELHKhJefdt0pqBdiCw3wPjovNp3KfwUy4hnV4s9rKmv4P5YZkALvTsurix~U5TjXR9GpDFeTPMlgLRXS86sd1yPnTk0Ajs9kEacCNCxQHb7QBpt1hrKcg__"
