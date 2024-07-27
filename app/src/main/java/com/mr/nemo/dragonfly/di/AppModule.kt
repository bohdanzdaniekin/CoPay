package com.mr.nemo.dragonfly.di

import com.mr.nemo.dragonfly.ui.screen.auth.signin.SignInViewModel
import com.mr.nemo.dragonfly.ui.screen.auth.signup.SignUpViewModel
import com.mr.nemo.dragonfly.ui.screen.onboarding.OnboardingViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

val viewModelModule = module {
    viewModelOf(::OnboardingViewModel)
    viewModelOf(::SignUpViewModel)
    viewModelOf(::SignInViewModel)
}

val appModule = module {
    includes(viewModelModule)
}
