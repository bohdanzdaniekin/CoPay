package com.mr.nemo.dragonfly.ui.screen.auth.signup

import com.mr.nemo.dragonfly.domain.entity.auth.signup.SignUpField
import com.mr.nemo.dragonfly.domain.entity.auth.signup.SignUpPageContent
import com.mr.nemo.dragonfly.ui.base.BaseViewModel
import com.mr.nemo.dragonfly.ui.entitiy.signup.SignUpScreenEffect
import com.mr.nemo.dragonfly.ui.entitiy.signup.SignUpScreenEvent
import com.mr.nemo.dragonfly.ui.entitiy.signup.SignUpScreenState
import com.mr.nemo.dragonfly.ui.entitiy.signup.SignUpVerificationPageEvent
import com.mr.nemo.dragonfly.ui.entitiy.signup.SignUpVerificationPageState
import kotlinx.coroutines.flow.MutableStateFlow
import org.koin.android.annotation.KoinViewModel

@KoinViewModel
class SignUpViewModel : BaseViewModel<SignUpScreenState, SignUpScreenEffect, SignUpScreenEvent>() {

    override val _state = MutableStateFlow(SignUpScreenState())

    override fun onEvent(event: SignUpScreenEvent) {
        when (event) {
            SignUpScreenEvent.OnInfoClicked -> {
                // TODO:
            }
            SignUpScreenEvent.OnBackClicked -> {
                // TODO:
            }
            SignUpScreenEvent.OnNextClicked -> {
                // TODO:
            }
            is SignUpScreenEvent.OnFieldUpdated<*, *> -> {
                when(event.field) {
                    is SignUpField.Email -> TODO()
                    is SignUpField.Password -> TODO()
                    is SignUpField.Phone -> TODO()
                    is SignUpField.Username -> TODO()
                }
            }
            SignUpVerificationPageEvent.OnRefreshClicked -> {

            }
            is SignUpVerificationPageEvent.OnSecurityCodeUpdate -> {

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
            SignUpPageContent.VerificationPageContent(
                SignUpVerificationPageState()
            )
        )
    )

}
