package ui.entitiy.auth.signup.info

import domain.entity.auth.signup.InfoItem
import ui.entitiy.core.UiEvent

sealed interface SignUpInfoScreenEvent : UiEvent {

    data object OnBackClicked : SignUpInfoScreenEvent

    data class OnItemClicked(val item: InfoItem) : SignUpInfoScreenEvent
}
