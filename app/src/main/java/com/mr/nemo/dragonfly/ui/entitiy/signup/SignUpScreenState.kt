package com.mr.nemo.dragonfly.ui.entitiy.signup

import androidx.annotation.FloatRange
import androidx.compose.runtime.Immutable
import com.mr.nemo.dragonfly.domain.entity.auth.signup.SignUpPageContent
import com.mr.nemo.dragonfly.ui.entitiy.core.UiState

@Immutable
data class SignUpScreenState(
    val pages: List<SignUpPageContent> = emptyList(),
    val currentPage: Int = 0,
    @FloatRange(from = 0.0, to = 1.0)
    val progress: Float = 0f
) : UiState
