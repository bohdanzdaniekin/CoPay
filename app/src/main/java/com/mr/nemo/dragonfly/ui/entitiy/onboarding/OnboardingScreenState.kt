package com.mr.nemo.dragonfly.ui.entitiy.onboarding

import com.mr.nemo.dragonfly.domain.entity.OnboardingContent
import com.mr.nemo.dragonfly.ui.entitiy.core.UiState

data class OnboardingScreenState(
    val currentPage: Int = 0,
    val pages: List<OnboardingContent> = emptyList()
) : UiState
