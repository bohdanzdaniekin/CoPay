package com.mr.nemo.dragonfly.domain.entity.auth.signup

import com.mr.nemo.dragonfly.ui.entitiy.signup.SignUpVerificationPageState

sealed class SignUpPageContent(
    open val title: String,
    val isProgressVisible: Boolean = false
) {

    data class RegisterPageContent<T>(
        override val title: String,
        val field: SignUpField<T>
    ) : SignUpPageContent(title = title, isProgressVisible = true)

    data class VerificationPageContent(
        override val title: String,
        val state: SignUpVerificationPageState
    ) : SignUpPageContent(title = title, isProgressVisible = false)
}
