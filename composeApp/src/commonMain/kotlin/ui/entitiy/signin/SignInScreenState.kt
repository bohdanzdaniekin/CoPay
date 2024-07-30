package ui.entitiy.signin

import androidx.compose.runtime.Immutable
import ui.entitiy.core.UiState

@Immutable
data class SignInScreenState(
    val username: String = "",
    val password: String = "",
    val rememberMe: Boolean = false
) : UiState
