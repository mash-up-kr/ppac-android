package team.ppac.mypage.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import team.ppac.designsystem.FarmemeTheme
import team.ppac.designsystem.R
import team.ppac.designsystem.foundation.FarmemeRadius

@Composable
internal fun MyPageSpeechBubble(
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        MyPageSpeechBubbleMain()
        Image(
            painter = painterResource(R.drawable.img_speech_bubble_tail),
            contentDescription = null,
        )
    }
}

@Composable
private fun MyPageSpeechBubbleMain(
    modifier: Modifier = Modifier,
) {
    val strings = listOf(
        "밈 폼 미쳤다",
        "밈 천재가 되",
        "완전 럭키비키잖아~",
        "너 드립 좀 친다?",
        "하이하이!",
        "신나게 밈 흔들어!",
        "중꺾마!",
        "밈린이 라고할 뻔",
        "렛츠고 밈천재",
        "밈야호~"
    )
    val text = strings.random()

    Box(
        modifier = modifier
            .clip(FarmemeRadius.Radius25.shape)
            .background(color = FarmemeTheme.backgroundColor.primary)
            .padding(horizontal = 20.dp, vertical = 12.dp),
        contentAlignment = Alignment.Center,
    ) {
        Text(
            text = text,
            style = FarmemeTheme.typography.body.xLarge.semibold,
            color = FarmemeTheme.textColor.inverse,
        )
    }
}

@Preview
@Composable
private fun MyPageSpeechBubblePreview(
    modifier: Modifier = Modifier,
) {
    MyPageSpeechBubble()
}