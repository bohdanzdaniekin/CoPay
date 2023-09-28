package com.mr.nemo.dragonfly.ui.entitiy.onboarding

import androidx.compose.runtime.Immutable
import com.mr.nemo.dragonfly.domain.entity.OnboardingContent
import com.mr.nemo.dragonfly.ui.entitiy.core.UiState

@Immutable
data class OnboardingScreenState(
    val currentPage: Int = 0,
    val pages: List<OnboardingContent> = emptyList()
) : UiState
