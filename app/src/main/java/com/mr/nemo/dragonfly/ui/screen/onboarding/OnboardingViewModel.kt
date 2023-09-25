package com.mr.nemo.dragonfly.ui.screen.onboarding

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mr.nemo.dragonfly.R
import com.mr.nemo.dragonfly.domain.entity.OnboardingContent
import com.mr.nemo.dragonfly.ui.entitiy.onboarding.OnboardingScreenEffect
import com.mr.nemo.dragonfly.ui.entitiy.onboarding.OnboardingScreenEvent
import com.mr.nemo.dragonfly.ui.entitiy.onboarding.OnboardingScreenState
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.koin.android.annotation.KoinViewModel

@KoinViewModel
class OnboardingViewModel : ViewModel() {

    private val _effect = Channel<OnboardingScreenEffect>()
    val effect = _effect.receiveAsFlow()

    private val _state = MutableStateFlow(OnboardingScreenState())
    val state = _state.asStateFlow()

    init {
        viewModelScope.launch {
            _state.update { state ->
                // TODO: Move to usecase/repository
                state.copy(
                    pages = listOf(
                        OnboardingContent(
                            title = "Easy to manage money",
                            description = "Transfer and receive your money easily with dragonfly bank",
                            image = R.drawable.bg_onboarding_page_1,
                            hasMore = true
                        ),
                        OnboardingContent(
                            title = "Transfers between accounts",
                            description = "Transferring balances is very easy between dragonfly bank accounts",
                            image = R.drawable.bg_onboarding_page_2,
                            hasMore = false
                        ),
                        OnboardingContent(
                            title = "Choose as needed",
                            description = "Choose the savings you want to open, we have lots of services according to what you need",
                            image = R.drawable.bg_onboarding_page_3,
                            hasMore = false
                        )
                    )
                )
            }
        }
    }

    fun onEvent(event: OnboardingScreenEvent) {
        when (event) {
            OnboardingScreenEvent.OnScrollBackward -> {
                viewModelScope.launch {
                    _state.update { state ->
                        if (state.currentPage > 0) {
                            state.copy(
                                currentPage = (state.currentPage - 1).coerceAtLeast(0)
                            )
                        } else {
                            _effect.send(OnboardingScreenEffect.NavigateBackward())
                            state
                        }
                    }
                }
            }
            OnboardingScreenEvent.OnScrollForward -> {
                viewModelScope.launch {
                    _state.update { state ->
                        if (state.currentPage < state.pages.lastIndex) {
                            state.copy(
                                currentPage = state.currentPage + 1
                            )
                        } else {
                            _effect.send(OnboardingScreenEffect.NavigateForward())
                            state
                        }
                    }
                }
            }
            is OnboardingScreenEvent.OnCurrentPageUpdated -> {
                viewModelScope.launch {
                    _state.update { state ->
                        state.copy(currentPage = event.page)
                    }
                }
            }
        }
    }
}
