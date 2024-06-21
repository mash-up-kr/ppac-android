package team.ppac.sample

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import team.ppac.designsystem.FarmemeTheme
import team.ppac.designsystem.component.button.FarmemeCircleButton
import team.ppac.designsystem.component.button.FarmemeFilledButton
import team.ppac.designsystem.component.button.FarmemeWeakButton
import team.ppac.designsystem.component.chip.FarmemeChip
import team.ppac.designsystem.foundation.FarmemeIcon
import team.ppac.sample.mvi.SampleIntent

@Composable
fun SampleScreen(viewModel: SampleViewModel) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Button(
            onClick = {
                viewModel.intent(SampleIntent.ClickGetImagesButton)
            },
        ) {
            Text("이미지 로드")
        }

        if (state.isLoading) {
            Text(
                style = FarmemeTheme.typography.body.large.bold,
                fontSize = 30.sp,
                text = "로딩중",
            )
        }
        FarmemeCircleButton(
            backgroundColor = FarmemeTheme.backgroundColor.assistive,
            icon = { FarmemeIcon.Copy(Modifier.size(20.dp)) },
        )

        FarmemeWeakButton(
            backgroundColor = FarmemeTheme.backgroundColor.assistive,
            icon = { FarmemeIcon.Copy(Modifier.size(20.dp)) },
            text = "버튼",
            textColor = FarmemeTheme.textColor.primary,
        )

        FarmemeFilledButton(
            backgroundColor = FarmemeTheme.backgroundColor.primary,
            text = "버튼",
            textColor = FarmemeTheme.textColor.inverse,
        )

        FarmemeChip(
            text = "Text",
            isActive = true,
        )

        FarmemeChip(
            text = "Text",
            isActive = false,
        )

        LazyColumn {
            items(items = state.images) {
                AsyncImage(
                    model = it.imageUrl,
                    contentDescription = it.author,
                )
            }
        }
    }
}