package team.ppac.mypage.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import team.ppac.designsystem.FarmemeTheme
import team.ppac.designsystem.component.button.FarmemeFilledButton
import team.ppac.designsystem.foundation.FarmemeIcon
import team.ppac.mypage.mvi.MyPageTabType

@Composable
fun EmptyMemeContent(
    modifier: Modifier = Modifier,
    tabType: MyPageTabType,
    onUploadClick: () -> Unit = {},
) {
    val (title, content) = when (tabType) {
        MyPageTabType.MY_MEMES -> "올린 밈이 없어요" to "공유하고 싶은 밈이 있다면\n업로드해보세요."
        MyPageTabType.SAVED_MEMES -> "저장한 밈이 없어요" to "관심있는 밈을 저장하고\n필요할 때 편하게 사용해보세요."
    }

    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Spacer(modifier = Modifier.size(160.dp))
        FarmemeIcon.EmptyResult()
        Spacer(modifier = Modifier.size(20.dp))
        Text(
            text = title,
            textAlign = TextAlign.Center,
            style = FarmemeTheme.typography.heading.small.bold.copy(
                color = FarmemeTheme.textColor.primary
            ),
        )
        Spacer(modifier = Modifier.size(9.dp))
        Text(
            text = content,
            textAlign = TextAlign.Center,
            style = FarmemeTheme.typography.body.large.medium.copy(
                color = FarmemeTheme.textColor.tertiary
            ),
        )
        if (tabType == MyPageTabType.MY_MEMES) {
            Spacer(modifier = Modifier.size(20.dp))
            FarmemeFilledButton(
                text = "밈 올리기",
                onClick = onUploadClick,
            )
        }
        Spacer(modifier = Modifier.size(220.dp))
    }
}

@Preview(showBackground = true)
@Composable
private fun EmptyMemeContentPreview() {
    EmptyMemeContent(tabType = MyPageTabType.MY_MEMES)
}