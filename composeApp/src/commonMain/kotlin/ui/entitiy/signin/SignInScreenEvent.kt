package ui.entitiy.signin

import ui.entitiy.core.UiEvent

sealed interface SignInScreenEvent : UiEvent {

    data class OnUsernameChanged(val value: String) : SignInScreenEvent

    data class OnPasswordChanged(val value: String) : SignInScreenEvent

    data class OnRememberMeChecked(val isChecked: Boolean) : SignInScreenEvent

    data object OnRegisterClicked : SignInScreenEvent

    data object OnLoginClicked : SignInScreenEvent

    data object OnLoginWithGmailClicked : SignInScreenEvent

    data object OnForgotPasswordClicked : SignInScreenEvent
}
