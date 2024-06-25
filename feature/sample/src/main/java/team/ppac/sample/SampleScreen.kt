package team.ppac.sample

import android.graphics.Bitmap
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.sp
import androidx.core.graphics.drawable.toBitmap
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import team.ppac.common.android.extension.copyImageToClipBoard
import team.ppac.designsystem.FarmemeTheme
import team.ppac.sample.mvi.SampleIntent

@Composable
fun SampleScreen(viewModel: SampleViewModel) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        val context = LocalContext.current

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

        LazyColumn {
            items(items = state.images) {
                var bitmap = remember<Bitmap?> { null }

                AsyncImage(
                    modifier = Modifier.clickable {
                        bitmap?.let {
                            context.copyImageToClipBoard(it)
                        }
                    },
                    model = it.imageUrl,
                    contentDescription = "",
                    onSuccess = {
                        bitmap = it.result.drawable.toBitmap()
                    }
                )
            }
        }
    }
}
