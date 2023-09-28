package com.mr.nemo.dragonfly.ui.entitiy.signup.info

import com.mr.nemo.dragonfly.ui.entitiy.core.UiEffect
import java.util.UUID

sealed interface SignUpInfoScreenEffect : UiEffect {

    data class NavigateBack(
        val id: String = UUID.randomUUID().toString()
    ) : SignUpInfoScreenEffect

    data class NavigateToInfoItem(
        val id: String = UUID.randomUUID().toString()
    ) : SignUpInfoScreenEffect
}
