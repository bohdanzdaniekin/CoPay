package com.mr.nemo.dragonfly.ui.entitiy.signin

import com.mr.nemo.dragonfly.ui.entitiy.core.UiEffect
import java.util.UUID

sealed interface SignInScreenEffect : UiEffect {

    data class NavigateForward(
        val id: String = UUID.randomUUID().toString()
    ) : SignInScreenEffect

    data class NavigateToSignUp(
        val id: String = UUID.randomUUID().toString()
    ) : SignInScreenEffect

    data class NavigateToForgotPassword(
        val id: String = UUID.randomUUID().toString()
    ) : SignInScreenEffect

    data class LoginWithGmail(
        val id: String = UUID.randomUUID().toString()
    ) : SignInScreenEffect
}
