package com.mr.nemo.dragonfly.ui.entitiy.onboarding

import com.mr.nemo.dragonfly.ui.entitiy.core.UiEffect
import java.util.UUID

sealed interface OnboardingEffect : UiEffect {

    data class NavigateForward(val id: String = UUID.randomUUID().toString()) : OnboardingEffect

    data class NavigateBackward(val id: String = UUID.randomUUID().toString()) : OnboardingEffect
}
