package ui.screen.onboarding

import domain.entity.OnboardingContent
import dragonfly.composeapp.generated.resources.Res
import dragonfly.composeapp.generated.resources.bg_onboarding_page_1
import dragonfly.composeapp.generated.resources.bg_onboarding_page_2
import dragonfly.composeapp.generated.resources.bg_onboarding_page_3
import ui.entitiy.onboarding.OnboardingScreenEffect
import ui.entitiy.onboarding.OnboardingScreenEvent
import ui.entitiy.onboarding.OnboardingScreenState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import ui.base.BaseViewModel

class OnboardingViewModel :
    BaseViewModel<OnboardingScreenState, OnboardingScreenEffect, OnboardingScreenEvent>() {

    override val _state = MutableStateFlow(OnboardingScreenState())
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
                            image = Res.drawable.bg_onboarding_page_1,
                            hasMore = true
                        ),
                        OnboardingContent(
                            title = "Transfers between accounts",
                            description = "Transferring balances is very easy between dragonfly bank accounts",
                            image = Res.drawable.bg_onboarding_page_2,
                            hasMore = false
                        ),
                        OnboardingContent(
                            title = "Choose as needed",
                            description = "Choose the savings you want to open, we have lots of services according to what you need",
                            image = Res.drawable.bg_onboarding_page_3,
                            hasMore = false
                        )
                    )
                )
            }
        }
    }

    override fun onEvent(event: OnboardingScreenEvent) {
        when (event) {
            OnboardingScreenEvent.OnScrollBackward -> {
                viewModelScope.launch {
                    _state.update { state ->
                        if (state.currentPage > 0) {
                            state.copy(
                                currentPage = (state.currentPage - 1).coerceAtLeast(0)
                            )
                        } else {
                            emitEffect(OnboardingScreenEffect.NavigateBackward())
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
                            emitEffect(OnboardingScreenEffect.NavigateForward())
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
