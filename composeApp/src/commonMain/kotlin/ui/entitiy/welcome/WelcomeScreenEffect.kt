package ui.entitiy.welcome

import ui.entitiy.core.UiEffect
import utils.extensions.randomUUID

sealed interface WelcomeScreenEffect : UiEffect {

    data class NavigateToAuth(
        val id: String = randomUUID()
    ) : WelcomeScreenEffect

    data class NavigateToHome(
        val id: String = randomUUID()
    ) : WelcomeScreenEffect
}
