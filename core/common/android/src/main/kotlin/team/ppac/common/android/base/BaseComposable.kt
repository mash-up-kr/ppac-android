package team.ppac.common.android.base

import androidx.compose.material.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import team.ppac.common.android.FarmemeSnackbarHost
import team.ppac.designsystem.component.snackbar.FarmemeSnackbar
import timber.log.Timber

@Composable
fun <S: UiState, SE: UiSideEffect, I: UiIntent> BaseComposable(
    viewModel: BaseViewModel<S, SE, I>,
    content: @Composable (S) -> Unit,
) = viewModel.apply {
    val state: S = state.collectAsStateWithLifecycle().value

    val snackBarScope = rememberCoroutineScope()
    val snackBarHostState = remember { SnackbarHostState() }

    var snackbarMessage by remember { mutableStateOf("") }

    FarmemeSnackbarHost(snackbarHostState = snackBarHostState) {
        FarmemeSnackbar(message = snackbarMessage)
    }

    LaunchedEffect(key1 = viewModel) {
        snackbarEffect.collectLatest { message ->
            snackBarScope.launch {
                snackbarMessage = message
                Timber.e("SnackbarMessage: $snackbarMessage")
                snackBarHostState.showSnackbar(snackbarMessage)
            }
        }
    }

    content(state)
}
