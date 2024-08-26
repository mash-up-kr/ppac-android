package team.ppac.common.android.util

import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner

@Composable
fun ComposableLifecycle(
    lifecycleOwner: LifecycleOwner = LocalLifecycleOwner.current,
    onLifecycleEvent: (LifecycleOwner, Lifecycle.Event) -> Unit,
) {
    DisposableEffect(key1 = lifecycleOwner) {
        val observer = LifecycleEventObserver { owner, event ->
            onLifecycleEvent(owner, event)
        }

        lifecycleOwner.lifecycle.addObserver(observer)

        onDispose {
            lifecycleOwner.lifecycle.removeObserver(observer)
        }
    }
}