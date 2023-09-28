package com.mr.nemo.dragonfly.ui.screen.auth.signup

import androidx.lifecycle.ViewModel
import com.mr.nemo.dragonfly.domain.entity.auth.signup.SignUpField
import com.mr.nemo.dragonfly.domain.entity.auth.signup.SignUpPageContent
import com.mr.nemo.dragonfly.ui.entitiy.signup.SignUpScreenEffect
import com.mr.nemo.dragonfly.ui.entitiy.signup.SignUpScreenEvent
import com.mr.nemo.dragonfly.ui.entitiy.signup.SignUpScreenState
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import org.koin.android.annotation.KoinViewModel

@KoinViewModel
class SignUpViewModel : ViewModel() {

    private val _effect = Channel<SignUpScreenEffect>()
    val effect = _effect.receiveAsFlow()

    private val _state = MutableStateFlow(SignUpScreenState())
    val state = _state.asStateFlow()

    fun onEvent(event: SignUpScreenEvent) {
        when (event) {
            SignUpScreenEvent.OnBackClicked -> {
                // TODO:
            }
            SignUpScreenEvent.OnInfoClicked -> {
                // TODO:
            }
            SignUpScreenEvent.OnNextClicked -> {
                // TODO:
            }
            is SignUpScreenEvent.OnUsernameChanged -> {
                // TODO:
            }
        }
    }
}

fun signUpSteps(withGoogle: Boolean) = buildList {
    if (!withGoogle) {
        add(
            SignUpPageContent.RegisterPageContent(
                SignUpField.Email(
                    title = "Email",
                    description = "Enter your email to register"
                )
            )
        )
    }
    addAll(
        listOf(
            SignUpPageContent.RegisterPageContent(
                SignUpField.Username(
                    title = "Username",
                    description = "Enter your username to register"
                )
            ),
            SignUpPageContent.RegisterPageContent(
                SignUpField.Password(
                    title = "Password",
                    description = "Enter your password to register"
                )
            ),
            SignUpPageContent.RegisterPageContent(
                SignUpField.Phone(
                    title = "Phone",
                    description = "Enter your phone to register"
                )
            ),
            SignUpPageContent.VerificationPageContent
        )
    )

}
