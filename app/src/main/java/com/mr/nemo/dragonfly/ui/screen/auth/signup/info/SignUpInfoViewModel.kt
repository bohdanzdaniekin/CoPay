package com.mr.nemo.dragonfly.ui.screen.auth.signup.info

import com.mr.nemo.dragonfly.ui.base.BaseViewModel
import com.mr.nemo.dragonfly.ui.entitiy.signup.info.SignUpInfoScreenEffect
import com.mr.nemo.dragonfly.ui.entitiy.signup.info.SignUpInfoScreenEvent
import com.mr.nemo.dragonfly.ui.entitiy.signup.info.SignUpInfoScreenState
import kotlinx.coroutines.flow.MutableStateFlow
import org.koin.android.annotation.KoinViewModel

@KoinViewModel
class SignUpInfoViewModel :
    BaseViewModel<SignUpInfoScreenState, SignUpInfoScreenEffect, SignUpInfoScreenEvent>() {

    override val _state = MutableStateFlow(SignUpInfoScreenState())
    override fun onEvent(event: SignUpInfoScreenEvent) {
        when (event) {
            else -> Unit
        }
    }
}
