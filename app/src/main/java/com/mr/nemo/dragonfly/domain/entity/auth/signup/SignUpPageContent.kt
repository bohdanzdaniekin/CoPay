package com.mr.nemo.dragonfly.domain.entity.auth.signup

import androidx.annotation.StringRes
import com.mr.nemo.dragonfly.R

sealed class SignUpPageContent(
    @StringRes val title: Int,
    val isProgressVisible: Boolean = false
) {

    data class RegisterPageContent<T>(
        val field: SignUpField<T>
    ) : SignUpPageContent(title = R.string.sign_up_page_title_register, isProgressVisible = true)

    data object VerificationPageContent :
        SignUpPageContent(title = R.string.sign_up_page_title_verify, isProgressVisible = false)
}
