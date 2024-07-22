package team.ppac.common.android.util

import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.statusBars
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.Dp

val systemBarHeight: Dp
    @Composable
    get() = WindowInsets.statusBars.asPaddingValues().calculateTopPadding()