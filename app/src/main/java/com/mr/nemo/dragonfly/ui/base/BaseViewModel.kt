package com.mr.nemo.dragonfly.ui.base

import androidx.lifecycle.ViewModel
import com.mr.nemo.dragonfly.ui.entitiy.core.UiEffect
import com.mr.nemo.dragonfly.ui.entitiy.core.UiEvent
import com.mr.nemo.dragonfly.ui.entitiy.core.UiState
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.plus
import kotlin.coroutines.CoroutineContext
import androidx.lifecycle.viewModelScope as lifecycleViewModelScope

@Suppress("PropertyName")
abstract class BaseViewModel<State : UiState, Effect : UiEffect, Event : UiEvent> : ViewModel() {

    private val exceptionHandler = CoroutineExceptionHandler(::onError)
    protected open fun onError(context: CoroutineContext, throwable: Throwable) = Unit

    protected val viewModelScope = lifecycleViewModelScope + exceptionHandler

    private val _effect = Channel<Effect>(Channel.BUFFERED)
    val effect = _effect.receiveAsFlow()

    abstract val _state: MutableStateFlow<State>

    protected fun emitEffect(effect: Effect) {
        viewModelScope.launch {
            _effect.send(effect)
        }
    }

    protected inline fun updateState(function: (State) -> State) {
        _state.update(function)
    }

    abstract fun onEvent(event: Event)
}
