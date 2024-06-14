package team.ppac.sample

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
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
                fontSize = 30.sp,
                text = "로딩중",
            )
        }

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