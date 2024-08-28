package ui.entitiy.welcome

import ui.entitiy.core.UiEvent

sealed interface WelcomeScreenEvent : UiEvent {

    data object OnInit : WelcomeScreenEvent
}