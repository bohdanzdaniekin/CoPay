package ui.entitiy.signup.info

import ui.entitiy.core.UiEffect
import utils.extensions.randomUUID

sealed interface SignUpInfoScreenEffect : UiEffect {

    data class NavigateBack(
        val id: String = randomUUID()
    ) : SignUpInfoScreenEffect

    data class NavigateToInfoItem(
        val id: String = randomUUID()
    ) : SignUpInfoScreenEffect
}
