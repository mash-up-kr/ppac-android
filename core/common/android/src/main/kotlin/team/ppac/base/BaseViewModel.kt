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

    private val _state = MutableStateFlow(initialState)// 늘 값이 있어
    val state = _state.asStateFlow()

    private val _sideEffect: MutableSharedFlow<SE> = MutableSharedFlow() // 없을수도 있고
    val sideEffect = _sideEffect.asSharedFlow()

    protected val currentState: S
        get() = _state.value

    fun intent(intent: I) { // 사용완료
        viewModelScope.launch {
            handleIntent(intent)
        }
    }

    protected abstract suspend fun handleIntent(intent: I)

    protected fun reduce(reduce: S.() -> S) {
        val state = currentState.reduce()
        _state.value = state
    }

    protected fun postSideEffect(sideEffect: SE) {
        viewModelScope.launch { _sideEffect.emit(sideEffect) }
    }
}