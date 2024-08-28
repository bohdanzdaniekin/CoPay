package ui.screen.auth.signup.info

import ui.entitiy.auth.signup.info.SignUpInfoScreenEffect
import ui.entitiy.auth.signup.info.SignUpInfoScreenEvent
import ui.entitiy.auth.signup.info.SignUpInfoScreenState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import ui.base.BaseViewModel

class SignUpInfoViewModel :
    BaseViewModel<SignUpInfoScreenState, SignUpInfoScreenEffect, SignUpInfoScreenEvent>() {

    override val _state = MutableStateFlow(SignUpInfoScreenState())
    val state = _state.asStateFlow()

    override fun onEvent(event: SignUpInfoScreenEvent) {
        when (event) {
            else -> Unit
        }
    }
}
