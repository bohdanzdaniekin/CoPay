package com.mr.nemo.dragonfly.ui.entitiy.signup

import com.mr.nemo.dragonfly.ui.entitiy.core.UiEffect
import java.util.UUID

sealed interface SignUpScreenEffect : UiEffect {

    data class NavigateBack(
        val id: String = UUID.randomUUID().toString()
    ) : SignUpScreenEffect

    data class NavigateToSignUpInfo(
        val id: String = UUID.randomUUID().toString()
    ) : SignUpScreenEffect
}
