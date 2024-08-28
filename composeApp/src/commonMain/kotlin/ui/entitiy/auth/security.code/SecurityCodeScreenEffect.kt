package ui.entitiy.auth.security.code

import ui.entitiy.core.UiEffect
import utils.extensions.randomUUID

sealed interface SecurityCodeScreenEffect : UiEffect {

    data class NavigateBack(
        val id: String = randomUUID()
    ) : SecurityCodeScreenEffect

    data class NavigateForward(
        val id: String = randomUUID()
    ) : SecurityCodeScreenEffect
}
