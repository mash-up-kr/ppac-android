package team.ppac.recommendation.component

import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieAnimatable
import com.airbnb.lottie.compose.rememberLottieComposition
import team.ppac.designsystem.R
import team.ppac.designsystem.util.extension.noRippleClickable

@Composable
internal fun UploadButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
) {
    val lottieComposition by rememberLottieComposition(
        LottieCompositionSpec.RawRes(R.raw.upload_button)
    )
    val lottieAnimatable = rememberLottieAnimatable()
    LaunchedEffect(lottieComposition) {
        lottieAnimatable.animate(
            composition = lottieComposition,
            iterations = LottieConstants.IterateForever,
        )
    }
    LottieAnimation(
        modifier = modifier
            .size(
                width = 130.dp,
                height = 36.dp,
            )
            .noRippleClickable(onClick = onClick),
        composition = lottieComposition,
        progress = { lottieAnimatable.progress },
    )
}