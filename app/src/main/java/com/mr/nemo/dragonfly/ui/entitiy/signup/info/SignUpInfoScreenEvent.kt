package com.mr.nemo.dragonfly.ui.entitiy.signup.info

import com.mr.nemo.dragonfly.domain.entity.auth.signup.InfoItem
import com.mr.nemo.dragonfly.ui.entitiy.core.UiEvent

sealed interface SignUpInfoScreenEvent : UiEvent {

    data object OnBackClicked : SignUpInfoScreenEvent

    data class OnItemClicked(val item: InfoItem) : SignUpInfoScreenEvent
}
