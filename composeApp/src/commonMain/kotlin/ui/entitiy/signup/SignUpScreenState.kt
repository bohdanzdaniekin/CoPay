package ui.entitiy.signup

import androidx.annotation.FloatRange
import androidx.compose.runtime.Immutable
import domain.entity.auth.signup.SignUpPageContent
import ui.entitiy.core.UiState

@Immutable
data class SignUpScreenState(
    val pages: List<SignUpPageContent> = emptyList(),
    val currentPage: Int = 0,
    @FloatRange(from = 0.0, to = 1.0)
    val progress: Float = 0f
) : UiState
