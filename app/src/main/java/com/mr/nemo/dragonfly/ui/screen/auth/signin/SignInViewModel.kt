package com.mr.nemo.dragonfly.ui.screen.auth.signin

import com.mr.nemo.dragonfly.ui.base.BaseViewModel
import com.mr.nemo.dragonfly.ui.entitiy.signin.SignInScreenEffect
import com.mr.nemo.dragonfly.ui.entitiy.signin.SignInScreenEvent
import com.mr.nemo.dragonfly.ui.entitiy.signin.SignInScreenState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class SignInViewModel : BaseViewModel<SignInScreenState, SignInScreenEffect, SignInScreenEvent>() {

    override val _state = MutableStateFlow(SignInScreenState())
    val state = _state.asStateFlow()

    override fun onEvent(event: SignInScreenEvent) {
        when (event) {
            is SignInScreenEvent.OnUsernameChanged -> {
                updateUsername(event.value)
            }
            is SignInScreenEvent.OnPasswordChanged -> {
                updatePassword(event.value)
            }
            is SignInScreenEvent.OnRememberMeChecked -> {
                updateRememberMe(event.isChecked)
            }
            SignInScreenEvent.OnForgotPasswordClicked -> {
                emitEffect(SignInScreenEffect.NavigateToForgotPassword())
            }
            SignInScreenEvent.OnLoginClicked -> {
                // TODO: Implement logging in
                emitEffect(SignInScreenEffect.NavigateForward())
            }
            SignInScreenEvent.OnLoginWithGmailClicked -> {
                emitEffect(SignInScreenEffect.LoginWithGmail())
            }
            SignInScreenEvent.OnRegisterClicked -> {
                emitEffect(SignInScreenEffect.NavigateToSignUp())
            }
        }
    }

    private fun updateUsername(username: String) {
        viewModelScope.launch {
            _state.update { state ->
                state.copy(username = username)
            }
        }
    }

    private fun updatePassword(password: String) {
        viewModelScope.launch {
            _state.update { state ->
                state.copy(password = password)
            }
        }
    }

    private fun updateRememberMe(isChecked: Boolean) {
        viewModelScope.launch {
            _state.update { state ->
                state.copy(rememberMe = isChecked)
            }
        }
    }
}
