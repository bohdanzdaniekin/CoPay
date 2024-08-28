package ui.entitiy.auth.signup

import domain.entity.auth.signup.SignUpField
import ui.component.securitycode.SecurityCodeState
import ui.entitiy.core.UiEvent

sealed interface SignUpScreenEvent : UiEvent {

    data class OnFieldUpdated<T, F : SignUpField<T>>(val field: F) : SignUpScreenEvent

    data class OnInit(val withGoogle: Boolean) : SignUpScreenEvent

    data object OnNextClicked : SignUpScreenEvent

    data object OnBackClicked : SignUpScreenEvent

    data object OnInfoClicked : SignUpScreenEvent
}

sealed interface SignUpVerificationPageEvent : SignUpScreenEvent {

    data object OnRefreshClicked : SignUpVerificationPageEvent

    data class OnSecurityCodeUpdate(val code: SecurityCodeState) : SignUpVerificationPageEvent
}
