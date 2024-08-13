package team.ppac.common.android.base

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.Firebase
import com.google.firebase.crashlytics.crashlytics
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.CoroutineStart
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import team.ppac.common.android.BuildConfig
import team.ppac.common.android.SnackbarData
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.EmptyCoroutineContext

abstract class BaseViewModel<S : UiState, SE : UiSideEffect, I : UiIntent>(
    savedStateHandle: SavedStateHandle,
) : ViewModel() {

    private val crashlytics by lazy { Firebase.crashlytics }
    private val initialState: S by lazy { createInitialState(savedStateHandle) }

    protected abstract fun createInitialState(savedStateHandle: SavedStateHandle): S

    protected abstract suspend fun handleIntent(intent: I)

    private val _state = MutableStateFlow(initialState)
    val state = _state.asStateFlow()

    private val _sideEffect: MutableSharedFlow<SE> = MutableSharedFlow()
    val sideEffect = _sideEffect.asSharedFlow()

    private val _snackbarEffect = MutableSharedFlow<SnackbarData>()
    val snackbarEffect = _snackbarEffect.asSharedFlow()

    // Get current state
    protected val currentState: S
        get() = _state.value

    protected val ceh = CoroutineExceptionHandler { _, throwable ->
        if (!BuildConfig.DEBUG) {
            crashlytics.recordException(throwable)
        }
        handleClientException(throwable)
    }

    fun showSnackbar(message: String, icon: @Composable (() -> Unit)? = null) = launch {
        _snackbarEffect.emit(SnackbarData(message, icon))
    }

    fun intent(intent: I) {
        launch {
            handleIntent(intent)
        }
    }

    protected fun reduce(reduce: S.() -> S) {
        val state = currentState.reduce()
        _state.value = state
    }

    protected fun postSideEffect(sideEffect: SE) {
        viewModelScope.launch { _sideEffect.emit(sideEffect) }
    }

    protected inline fun launch(
        context: CoroutineContext = EmptyCoroutineContext,
        start: CoroutineStart = CoroutineStart.DEFAULT,
        crossinline action: suspend CoroutineScope.() -> Unit,
    ): Job {
        return viewModelScope.launch(context + ceh, start = start) {
            action()
        }
    }

    abstract fun handleClientException(throwable: Throwable)
}