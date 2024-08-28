package ui.entitiy.auth.security.code

import ui.component.securitycode.SecurityCodeState
import ui.entitiy.core.UiState

data class SecurityCodeScreenState(
    val securityCode: SecurityCodeState = SecurityCodeState()
) : UiState