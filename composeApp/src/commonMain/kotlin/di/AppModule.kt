package di

import org.koin.compose.viewmodel.dsl.viewModelOf
import org.koin.dsl.module
import ui.screen.auth.signin.SignInViewModel
import ui.screen.auth.signup.SignUpViewModel
import ui.screen.onboarding.OnboardingViewModel

val viewModelModule = module {
    viewModelOf(::OnboardingViewModel)
    viewModelOf(::SignUpViewModel)
    viewModelOf(::SignInViewModel)
}

val appModule = module {
    includes(viewModelModule)
}
