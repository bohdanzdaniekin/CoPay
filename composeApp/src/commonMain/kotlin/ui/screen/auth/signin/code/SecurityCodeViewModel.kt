package ui.screen.auth.signin.code

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import ui.base.BaseViewModel
import ui.component.securitycode.SecurityCodeState
import ui.entitiy.auth.security.code.SecurityCodeScreenEffect
import ui.entitiy.auth.security.code.SecurityCodeScreenEvent
import ui.entitiy.auth.security.code.SecurityCodeScreenState

class SecurityCodeViewModel :
    BaseViewModel<SecurityCodeScreenState, SecurityCodeScreenEffect, SecurityCodeScreenEvent>() {

    override val _state = MutableStateFlow(SecurityCodeScreenState())
    val state = _state.asStateFlow()

    override fun onEvent(event: SecurityCodeScreenEvent) {
        when (event) {
            is SecurityCodeScreenEvent.SecurityCodeChanged -> {
                updateSecurityCode(event.securityCode)
                if (validate(event.securityCode)) {
                    emitEffect(SecurityCodeScreenEffect.NavigateForward())
                }
            }
            else -> {}
        }
    }

    private fun validate(code: SecurityCodeState): Boolean {
        return code.isFilled
    }

    private fun updateSecurityCode(code: SecurityCodeState) {
        viewModelScope.launch {
            _state.update { state ->
                state.copy(securityCode = code)
            }
        }
    }
}