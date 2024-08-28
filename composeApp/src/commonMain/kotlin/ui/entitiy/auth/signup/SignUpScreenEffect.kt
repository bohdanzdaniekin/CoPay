package ui.entitiy.auth.signup

import ui.entitiy.core.UiEffect
import utils.extensions.randomUUID

sealed interface SignUpScreenEffect : UiEffect {

    data class NavigateBack(
        val id: String = randomUUID()
    ) : SignUpScreenEffect

    data class NavigateToSignUpInfo(
        val id: String = randomUUID()
    ) : SignUpScreenEffect
}
