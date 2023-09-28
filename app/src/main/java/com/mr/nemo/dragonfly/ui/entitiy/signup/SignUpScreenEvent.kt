package com.mr.nemo.dragonfly.ui.entitiy.signup

import com.mr.nemo.dragonfly.ui.entitiy.core.UiEvent

sealed interface SignUpScreenEvent : UiEvent {

    data class OnUsernameChanged(val value: String) : SignUpScreenEvent

    data object OnNextClicked : SignUpScreenEvent

    data object OnBackClicked : SignUpScreenEvent

    data object OnInfoClicked : SignUpScreenEvent
}
