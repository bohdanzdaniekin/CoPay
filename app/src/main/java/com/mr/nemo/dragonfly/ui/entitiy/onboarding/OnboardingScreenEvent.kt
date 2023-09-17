package com.mr.nemo.dragonfly.ui.entitiy.onboarding

import com.mr.nemo.dragonfly.ui.entitiy.core.UiEvent

sealed interface OnboardingScreenEvent : UiEvent {

    data object OnScrollForward : OnboardingScreenEvent

    data object OnScrollBackward : OnboardingScreenEvent

    data class OnCurrentPageUpdated(val page: Int) : OnboardingScreenEvent
}
