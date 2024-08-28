package ui.entitiy.auth.security.code

import ui.component.securitycode.SecurityCodeState
import ui.entitiy.core.UiEvent

sealed interface SecurityCodeScreenEvent : UiEvent {

    data class SecurityCodeChanged(
        val securityCode: SecurityCodeState
    ) : SecurityCodeScreenEvent
}
