package ui.entitiy.auth.signin

import ui.entitiy.core.UiEffect
import utils.extensions.randomUUID

sealed interface SignInScreenEffect : UiEffect {

    data class NavigateForward(
        val id: String = randomUUID()
    ) : SignInScreenEffect

    data class NavigateToSignUp(
        val id: String = randomUUID()
    ) : SignInScreenEffect

    data class NavigateToForgotPassword(
        val id: String = randomUUID()
    ) : SignInScreenEffect

    data class LoginWithGmail(
        val id: String = randomUUID()
    ) : SignInScreenEffect
}
