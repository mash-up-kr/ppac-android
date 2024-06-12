package team.ppac.sample

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import team.ppac.sample.mvi.SampleIntent

@Composable
fun SampleScreen(viewModel: SampleViewModel) {
    Column(modifier = Modifier.fillMaxSize()) {
        Button(
            onClick = {
                viewModel.intent(SampleIntent.ClickGetImagesButton)
            },
        ) {
            Text("이미지 로드")
        }


    }
}