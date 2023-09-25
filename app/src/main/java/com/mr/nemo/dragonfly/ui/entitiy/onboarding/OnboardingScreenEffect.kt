package com.mr.nemo.dragonfly.ui.entitiy.onboarding

import com.mr.nemo.dragonfly.ui.entitiy.core.UiEffect
import java.util.UUID

sealed interface OnboardingScreenEffect : UiEffect {

    data class NavigateForward(
        val id: String = UUID.randomUUID().toString()
    ) : OnboardingScreenEffect

    data class NavigateBackward(
        val id: String = UUID.randomUUID().toString()
    ) : OnboardingScreenEffect
}
