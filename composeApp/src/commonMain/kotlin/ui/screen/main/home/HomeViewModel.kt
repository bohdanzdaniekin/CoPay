package ui.screen.main.home

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import ui.base.BaseViewModel
import ui.entitiy.main.home.HomeScreenEffect
import ui.entitiy.main.home.HomeScreenEvent
import ui.entitiy.main.home.HomeScreenState

class HomeViewModel :
    BaseViewModel<HomeScreenState, HomeScreenEffect, HomeScreenEvent>() {

    override val _state = MutableStateFlow(HomeScreenState())
    val state = _state.asStateFlow()

    override fun onEvent(event: HomeScreenEvent) {
        when (event) {
            HomeScreenEvent.CreatePocket -> {
                // TODO: Implement
            }
            HomeScreenEvent.RequestMoney -> {
                // TODO: Implement
            }
            HomeScreenEvent.SendMoney -> {
                // TODO: Implement
            }
            HomeScreenEvent.UpdateCurrencies -> {
                // TODO: Implement
            }
            HomeScreenEvent.ViewHistory -> {
                // TODO: Implement
            }
            HomeScreenEvent.CloseAd -> {

            }
            HomeScreenEvent.ForwardFromAd -> {

            }
        }
    }
}