package team.ppac.common.android

import androidx.compose.runtime.Composable

data class SnackbarData(
    val message: String,
    val icon: @Composable (() -> Unit)? = null
)
