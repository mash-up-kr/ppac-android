package team.ppac.common.android

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeOut
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.util.fastFilterNotNull
import androidx.compose.ui.util.fastForEach
import androidx.compose.ui.util.fastMap
import androidx.compose.ui.util.fastMapTo
import kotlinx.coroutines.delay

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
    FadeInFadeOutWithScale(
        current = snackbarHostState.currentSnackbarData,
        modifier = modifier
            .fillMaxSize()
            .padding(top = 10.dp),
        content = snackbar
    )
}

@Composable
private fun FadeInFadeOutWithScale(
    current: SnackbarData?,
    modifier: Modifier = Modifier,
    content: @Composable (SnackbarData) -> Unit,
) {
    val state = remember { FadeInFadeOutState<SnackbarData?>() }
    if (current != state.current) {
        state.current = current
        val keys = state.items.fastMap { it.key }.toMutableList()
        if (!keys.contains(current)) {
            keys.add(current)
        }
        state.items.clear()
        keys.fastFilterNotNull().fastMapTo(state.items) { key ->
            FadeInFadeOutAnimationItem(key) { children ->
                AnimatedVisibility(
                    visible = key == current,
                    //                    enter = fadeIn(animationSpec = tween(durationMillis = 1500, easing = LinearEasing)),
                    exit = fadeOut(animationSpec = tween(500))
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

private class FadeInFadeOutState<T> {
    var current: Any? = Any()
    var items = mutableListOf<FadeInFadeOutAnimationItem<T>>()
    var scope: RecomposeScope? = null
}

private data class FadeInFadeOutAnimationItem<T>(
    val key: T,
    val transition: FadeInFadeOutTransition
)

private typealias FadeInFadeOutTransition = @Composable (content: @Composable () -> Unit) -> Unit