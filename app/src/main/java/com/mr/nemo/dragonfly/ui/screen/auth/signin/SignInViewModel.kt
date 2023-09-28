package com.mr.nemo.dragonfly.ui.screen.auth.signin

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mr.nemo.dragonfly.ui.entitiy.signin.SignInScreenEffect
import com.mr.nemo.dragonfly.ui.entitiy.signin.SignInScreenEvent
import com.mr.nemo.dragonfly.ui.entitiy.signin.SignInScreenState
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.koin.android.annotation.KoinViewModel

@KoinViewModel
class SignInViewModel : ViewModel() {

    private val _effect = Channel<SignInScreenEffect>()
    val effect = _effect.receiveAsFlow()

    private val _state = MutableStateFlow(SignInScreenState())
    val state = _state.asStateFlow()

    fun onEvent(event: SignInScreenEvent) {
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
                viewModelScope.launch {
                    _effect.send(SignInScreenEffect.NavigateToForgotPassword())
                }
            }
            SignInScreenEvent.OnLoginClicked -> {
                // TODO: Implement logging in
                viewModelScope.launch {
                    _effect.send(SignInScreenEffect.NavigateForward())
                }
            }
            SignInScreenEvent.OnLoginWithGmailClicked -> {
                viewModelScope.launch {
                    _effect.send(SignInScreenEffect.LoginWithGmail())
                }
            }
            SignInScreenEvent.OnRegisterClicked -> {
                viewModelScope.launch {
                    _effect.send(SignInScreenEffect.NavigateToSignUp())
                }
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
