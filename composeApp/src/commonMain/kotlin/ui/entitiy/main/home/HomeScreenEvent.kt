package ui.entitiy.main.home

import ui.entitiy.core.UiEvent

sealed interface HomeScreenEvent : UiEvent {
    data object SendMoney : HomeScreenEvent
    data object RequestMoney : HomeScreenEvent
    data object ViewHistory : HomeScreenEvent
    data object CreatePocket : HomeScreenEvent
    data object UpdateCurrencies : HomeScreenEvent
    data object ForwardFromAd : HomeScreenEvent
    data object CloseAd : HomeScreenEvent
}
