package com.mr.nemo.dragonfly.ui.entitiy.signup

import com.mr.nemo.dragonfly.domain.entity.auth.signup.SignUpField
import com.mr.nemo.dragonfly.ui.component.securitycode.SecurityCodeState
import com.mr.nemo.dragonfly.ui.entitiy.core.UiEvent

sealed interface SignUpScreenEvent : UiEvent {

    data class OnFieldUpdated<T, F : SignUpField<T>>(val field: F) : SignUpScreenEvent

    data object OnNextClicked : SignUpScreenEvent

    data object OnBackClicked : SignUpScreenEvent

    data object OnInfoClicked : SignUpScreenEvent
}

sealed interface SignUpVerificationPageEvent : SignUpScreenEvent {

    data object OnRefreshClicked : SignUpVerificationPageEvent

    data class OnSecurityCodeUpdate(val code: SecurityCodeState) : SignUpVerificationPageEvent
}
