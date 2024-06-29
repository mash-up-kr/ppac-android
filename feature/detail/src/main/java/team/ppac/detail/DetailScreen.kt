package team.ppac.detail

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Icon
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
@Composable
fun DetailScreen() {
    Box(
        modifier = Modifier.border(
            width = 2.dp,
            color = FarmemeTheme.borderColor.primary,
            shape = RoundedCornerShape(20.dp)
        ),
    ) {
        Column(
            modifier = Modifier.padding(10.dp), horizontalAlignment = Alignment.CenterHorizontally
        ) {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current).data(TEST_IMAGE_PREVIEW)
                    .crossfade(true).build(),
                placeholder = painterResource(team.ppac.designsystem.R.drawable.detail_sample),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier.clip(RoundedCornerShape(10.dp))
            )
            DetailTexts()
            DetailButton()
        }
    }
}

@Composable
fun DetailTexts() {
    Spacer(modifier = Modifier.height(25.dp))
    Text(text = "나는 공부를 찢어")
    Spacer(modifier = Modifier.height(5.dp))
    Text(text = "#공부 #학생 #시험기간 #힘듦 #피곤")
    Spacer(modifier = Modifier.height(11.dp))
    Text(text = "출처: 출처에 대한 내용이 들어갑니다.")
}

@Composable
fun DetailButton() {
    Button(onClick = {}) {
        Row {
            Icon(painter = painterResource(id = R.drawable.ic_kk), contentDescription = null)
            Icon(
                painter = painterResource(id = R.drawable.ic_funny),
                contentDescription = null
            )
        }
    }
}

@Composable
@Preview(showBackground = true)
fun PreviewDetailScreen() {
    DetailScreen()
}

private const val TEST_IMAGE_PREVIEW =
    "https://s3-alpha-sig.figma.com/img/2215/ecea/fcea7f8dbec74e16f56675f756edb8b5?Expires=1720396800&Key-Pair-Id=APKAQ4GOSFWCVNEHN3O4&Signature=P8TH9OlXy0-7cZDofJLFdsRUNDhXsiEaeRgPKZSvHAQM8jNRMRulpsz7s1cvBuQWRvoB1vfYpY1eGZdRyPGgZEWafyvNXDh9GxJS2n~PBNgGSxGy10c2uRU1OmOxE5hwMlci6BGGvLamKTO1LZ4A4yPEaCwPtVyZswWFbAXYdCSUMidW0zp94EDCCCcFPmOp0Un6usY7AsZ18bchDMY-iQmqSG9V8dqyQELHKhJefdt0pqBdiCw3wPjovNp3KfwUy4hnV4s9rKmv4P5YZkALvTsurix~U5TjXR9GpDFeTPMlgLRXS86sd1yPnTk0Ajs9kEacCNCxQHb7QBpt1hrKcg__"
