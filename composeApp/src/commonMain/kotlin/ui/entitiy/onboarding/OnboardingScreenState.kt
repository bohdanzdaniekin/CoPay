package ui.entitiy.onboarding

import androidx.compose.runtime.Immutable
import domain.entity.OnboardingContent
import ui.entitiy.core.UiState

@Immutable
data class OnboardingScreenState(
    val currentPage: Int = 0,
    val pages: List<OnboardingContent> = emptyList()
) : UiState
