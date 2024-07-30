package ui.entitiy.onboarding

import ui.entitiy.core.UiEffect
import utils.extensions.randomUUID

sealed interface OnboardingScreenEffect : UiEffect {

    data class NavigateForward(
        val id: String = randomUUID()
    ) : OnboardingScreenEffect

    data class NavigateBackward(
        val id: String = randomUUID()
    ) : OnboardingScreenEffect
}
