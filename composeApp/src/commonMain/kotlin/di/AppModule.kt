package di

import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module
import ui.screen.auth.signin.SignInViewModel
import ui.screen.auth.signin.code.SecurityCodeViewModel
import ui.screen.auth.signup.SignUpViewModel
import ui.screen.main.home.HomeViewModel
import ui.screen.onboarding.OnboardingViewModel
import ui.screen.welcome.WelcomeViewModel

val viewModelModule = module {
    viewModelOf(::WelcomeViewModel)
    viewModelOf(::OnboardingViewModel)

    // Auth viewmodels
    viewModelOf(::SignUpViewModel)
    viewModelOf(::SignInViewModel)
    viewModelOf(::SecurityCodeViewModel)

    // Main viewmodels
    viewModelOf(::HomeViewModel)
}

val appModule = module {
    includes(viewModelModule)
}
