package com.mr.nemo.dragonfly.ui.entitiy.signin

import androidx.compose.runtime.Immutable
import com.mr.nemo.dragonfly.ui.entitiy.core.UiState

@Immutable
data class SignInScreenState(
    val username: String = "",
    val password: String = "",
    val rememberMe: Boolean = false
) : UiState
