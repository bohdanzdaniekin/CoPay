package com.mr.nemo.dragonfly.domain.entity.auth.signup

import androidx.annotation.StringRes
import com.mr.nemo.dragonfly.R
import com.mr.nemo.dragonfly.ui.entitiy.signup.SignUpVerificationPageState

sealed class SignUpPageContent(
    @StringRes val title: Int,
    val isProgressVisible: Boolean = false
) {

    data class RegisterPageContent<T>(
        val field: SignUpField<T>
    ) : SignUpPageContent(title = R.string.sign_up_page_title_register, isProgressVisible = true)

    data class VerificationPageContent(
        val state: SignUpVerificationPageState
    ) : SignUpPageContent(title = R.string.sign_up_page_title_verify, isProgressVisible = false)
}
