package ui.entitiy.onboarding

import ui.entitiy.core.UiEvent

sealed interface OnboardingScreenEvent : UiEvent {

    data object OnScrollForward : OnboardingScreenEvent

    data object OnScrollBackward : OnboardingScreenEvent

    data class OnCurrentPageUpdated(val page: Int) : OnboardingScreenEvent
}
