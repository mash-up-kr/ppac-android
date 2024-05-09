package team.ppac.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

abstract class BaseViewModel<S : UiState, SE : UiSideEffect, I : UiIntent>(
    initialState: S,
) : ViewModel() {

    private val _uiState = MutableStateFlow(initialState)
    val state = _uiState.asStateFlow()


    private val _sideEffect: MutableSharedFlow<SE> = MutableSharedFlow()
    val sideEffect = _sideEffect.asSharedFlow()

    // Get current state
    protected val currentState: S
        get() = _uiState.value

    fun intent(intent: I) {
        viewModelScope.launch {
            handleIntent(intent)
        }
    }

    protected abstract suspend fun handleIntent(intent: I)

    protected fun reduce(reduce: S.() -> S) {
        val state = currentState.reduce()
        _uiState.value = state
    }

    protected fun postSideEffect(sideEffect: SE) {
        viewModelScope.launch { _sideEffect.emit(sideEffect) }
    }
}