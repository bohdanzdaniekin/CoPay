package ui.screen.auth.signup

import androidx.compose.runtime.Composable
import domain.entity.auth.signup.SignUpField
import domain.entity.auth.signup.SignUpPageContent
import dragonfly.composeapp.generated.resources.Res
import dragonfly.composeapp.generated.resources.sign_up_page_title_register
import dragonfly.composeapp.generated.resources.sign_up_page_title_verify
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import org.jetbrains.compose.resources.stringResource
import ui.base.BaseViewModel
import ui.entitiy.auth.signup.SignUpScreenEffect
import ui.entitiy.auth.signup.SignUpScreenEvent
import ui.entitiy.auth.signup.SignUpScreenState
import ui.entitiy.auth.signup.SignUpVerificationPageEvent
import ui.entitiy.auth.signup.SignUpVerificationPageState

class SignUpViewModel : BaseViewModel<SignUpScreenState, SignUpScreenEffect, SignUpScreenEvent>() {

    override val _state = MutableStateFlow(SignUpScreenState())
    val state = _state.asStateFlow()

    override fun onEvent(event: SignUpScreenEvent) {
        when (event) {
            is SignUpScreenEvent.OnInit -> {

            }
            SignUpScreenEvent.OnInfoClicked -> {
                // TODO:
            }
            SignUpScreenEvent.OnBackClicked -> {
                // TODO:
            }
            SignUpScreenEvent.OnNextClicked -> {
                // TODO:
            }
            is SignUpScreenEvent.OnFieldUpdated<*, *> -> {
                when(event.field) {
                    is SignUpField.Email -> TODO()
                    is SignUpField.Password -> TODO()
                    is SignUpField.Phone -> TODO()
                    is SignUpField.Username -> TODO()
                }
            }
            SignUpVerificationPageEvent.OnRefreshClicked -> {

            }
            is SignUpVerificationPageEvent.OnSecurityCodeUpdate -> {

            }
        }
    }
}

@Composable
fun signUpSteps(withGoogle: Boolean) = buildList {
    if (!withGoogle) {
        add(
            SignUpPageContent.RegisterPageContent(
                title = stringResource(resource = Res.string.sign_up_page_title_register),
                SignUpField.Email(
                    title = "Email",
                    description = "Enter your email to register"
                )
            )
        )
    }
    addAll(
        listOf(
            SignUpPageContent.RegisterPageContent(
                title = stringResource(resource = Res.string.sign_up_page_title_register),
                field = SignUpField.Username(
                    title = "Username",
                    description = "Enter your username to register"
                )
            ),
            SignUpPageContent.RegisterPageContent(
                title = stringResource(resource = Res.string.sign_up_page_title_register),
                field = SignUpField.Password(
                    title = "Password",
                    description = "Enter your password to register"
                )
            ),
            SignUpPageContent.RegisterPageContent(
                title = stringResource(resource = Res.string.sign_up_page_title_register),
                field = SignUpField.Phone(
                    title = "Phone",
                    description = "Enter your phone to register"
                )
            ),
            SignUpPageContent.VerificationPageContent(
                title = stringResource(resource = Res.string.sign_up_page_title_verify),
                state = SignUpVerificationPageState()
            )
        )
    )

}
