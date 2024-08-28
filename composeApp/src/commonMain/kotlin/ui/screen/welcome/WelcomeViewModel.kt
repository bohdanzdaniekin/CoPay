package ui.screen.welcome

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import ui.base.BaseViewModel
import ui.entitiy.welcome.WelcomeScreenEffect
import ui.entitiy.welcome.WelcomeScreenEvent
import ui.entitiy.welcome.WelcomeScreenState

class WelcomeViewModel :
    BaseViewModel<WelcomeScreenState, WelcomeScreenEffect, WelcomeScreenEvent>() {

    override val _state = MutableStateFlow(WelcomeScreenState)
    val state = _state.asStateFlow()

    override fun onEvent(event: WelcomeScreenEvent) {
        when (event) {
            WelcomeScreenEvent.OnInit -> {
                emitEffect(WelcomeScreenEffect.NavigateToAuth())
            }
            else -> {}
        }
    }
}
