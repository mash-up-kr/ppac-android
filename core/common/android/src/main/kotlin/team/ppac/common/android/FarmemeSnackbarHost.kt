package team.ppac.common.android

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Snackbar
import androidx.compose.material.SnackbarData
import androidx.compose.material.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.RecomposeScope
import androidx.compose.runtime.currentRecomposeScope
import androidx.compose.runtime.key
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.util.fastFilterNotNull
import androidx.compose.ui.util.fastForEach
import androidx.compose.ui.util.fastMap
import androidx.compose.ui.util.fastMapTo
import kotlinx.coroutines.delay
import team.ppac.common.android.util.systemBarHeight
import team.ppac.designsystem.component.snackbar.FarmemeSnackbar

@Composable
fun FarmemeSnackbarHost(
    modifier: Modifier = Modifier,
    snackbarHostState: SnackbarHostState,
    snackbar: @Composable (SnackbarData) -> Unit = { Snackbar(it) }
) {
    val currentSnackbarData = snackbarHostState.currentSnackbarData

    LaunchedEffect(currentSnackbarData) {
        if (currentSnackbarData != null) {
            delay(2000L)
            currentSnackbarData.dismiss()
        }
    }
    SlideInSlideOutWithScale(
        current = snackbarHostState.currentSnackbarData,
        modifier = modifier
            .fillMaxSize()
            .padding(top = 10.dp + systemBarHeight),
        content = snackbar
    )
}

@Composable
private fun SlideInSlideOutWithScale(
    current: SnackbarData?,
    modifier: Modifier = Modifier,
    content: @Composable (SnackbarData) -> Unit,
) {
    val state = remember { SlideInSlideOutState<SnackbarData?>() }
    val systemBarHeightPx = with(LocalDensity.current) { systemBarHeight.roundToPx() }

    if (current != state.current) {
        state.current = current
        val keys = state.items.fastMap { it.key }.toMutableList()
        if (!keys.contains(current)) {
            keys.add(current)
        }
        state.items.clear()
        keys.fastFilterNotNull().fastMapTo(state.items) { key ->
            SlideInSlideOutAnimationItem(key) { children ->
                AnimatedVisibility(
                    visible = key == current,
                    enter = slideInVertically(
                        initialOffsetY = { fullHeight -> fullHeight },
                        animationSpec = tween(
                            durationMillis = 200,
                        )
                    ),
                    exit = slideOutVertically(
                        targetOffsetY = { fullHeight -> -fullHeight - systemBarHeightPx },
                        animationSpec = tween(
                            durationMillis = 200,
                        )
                    ),
                ) {
                    children()
                }
            }
        }
    }

    Box(
        modifier = modifier,
        contentAlignment = Alignment.TopCenter
    ) {
        state.scope = currentRecomposeScope
        state.items.fastForEach { (item, opacity) ->
            key(item) {
                opacity {
                    content(item!!)
                }
            }
        }
    }
}

private class SlideInSlideOutState<T> {
    var current: Any? = Any()
    var items = mutableListOf<SlideInSlideOutAnimationItem<T>>()
    var scope: RecomposeScope? = null
}

private data class SlideInSlideOutAnimationItem<T>(
    val key: T,
    val transition: SlideInSlideOutTransition
)

private typealias SlideInSlideOutTransition = @Composable (content: @Composable () -> Unit) -> Unit

@Preview
@Composable
private fun FarmemeSnackbarHostPreview() {
    FarmemeSnackbarHost(
        snackbarHostState = remember { SnackbarHostState() },
        snackbar = {
            FarmemeSnackbar(message = "asdfasdf")
        }
    )
}