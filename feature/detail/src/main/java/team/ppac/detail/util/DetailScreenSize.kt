package team.ppac.detail.util

import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

internal enum class DetailScreenSize(val width: Dp, val height: Dp) {
    SMALL(360.dp, 640.dp),
    MEDIUM(360.dp, 800.dp),
    LARGE(430.dp, 932.dp);

    companion object {
        fun from(width: Dp, height: Dp): DetailScreenSize {
            return entries.find { screenSize ->
                height <= screenSize.height
            } ?: LARGE
        }
    }
}

internal fun DetailScreenSize.toImageSize(): Pair<Dp, Dp> {
    return when (this) {
        DetailScreenSize.SMALL -> Pair(300.dp, 320.dp)
        DetailScreenSize.MEDIUM -> Pair(330.dp, 352.dp)
        DetailScreenSize.LARGE -> Pair(366.dp, 390.dp)
    }
}