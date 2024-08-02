package ui.screen.auth.signin

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import dragonfly.composeapp.generated.resources.Res
import dragonfly.composeapp.generated.resources.button_forgot_password
import dragonfly.composeapp.generated.resources.button_login
import dragonfly.composeapp.generated.resources.button_login_with_gmail
import dragonfly.composeapp.generated.resources.button_register
import dragonfly.composeapp.generated.resources.hint_email_username
import dragonfly.composeapp.generated.resources.ic_google
import dragonfly.composeapp.generated.resources.other
import dragonfly.composeapp.generated.resources.register_part_first
import dragonfly.composeapp.generated.resources.remember_me
import dragonfly.composeapp.generated.resources.sign_in_subtitle
import dragonfly.composeapp.generated.resources.welcome
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.viewmodel.koinViewModel
import ui.component.appbar.UntitledTopAppBar
import ui.component.button.Checkbox
import ui.component.button.OutlinedButton
import ui.component.button.PrimaryButton
import ui.component.text.TitleText
import ui.component.textfield.BaseTextField
import ui.component.textfield.PasswordTextField
import ui.entitiy.signin.SignInScreenEffect
import ui.entitiy.signin.SignInScreenEvent
import ui.entitiy.signin.SignInScreenState
import ui.theme.DragonFlyTheme
import utils.extensions.collectAsEffect
import utils.extensions.collectAsStateWithLifecycle

@Composable
fun SignInScreen(
    viewModel: SignInViewModel = koinViewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    viewModel.effect.collectAsEffect { effect ->
        when (effect) {
            is SignInScreenEffect.LoginWithGmail -> {
                // TODO: To be implemented
            }
            is SignInScreenEffect.NavigateForward -> {
                // TODO: To be implemented
            }
            is SignInScreenEffect.NavigateToForgotPassword -> {
                // TODO: To be implemented
            }
            is SignInScreenEffect.NavigateToSignUp -> {
                // TODO: To be implemented
            }
        }
    }

    SignInScreen(
        state = state,
        onEvent = viewModel::onEvent
    )
}

@Composable
private fun SignInScreen(
    state: SignInScreenState,
    onEvent: (event: SignInScreenEvent) -> Unit,
    modifier: Modifier = Modifier
) {
    val spacing = DragonFlyTheme.spacing
    val typography = DragonFlyTheme.typography
    val colors = DragonFlyTheme.colors
    Scaffold(
        topBar = {
            UntitledTopAppBar(
                onLanguageClicked = { /*TODO*/ },
                onLogoClicked = { /*TODO*/ }
            )
        },
        containerColor = colors.neutral8,
        modifier = modifier
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .padding(horizontal = spacing.medium)
        ) {
            TitleText(
                text = stringResource(resource = Res.string.welcome),
                style = typography.header1.regular,
                color = colors.neutral2
            )

            Spacer(modifier = Modifier.height(spacing.medium))

            Text(
                text = stringResource(resource = Res.string.sign_in_subtitle),
                style = typography.text2.regular,
                color = colors.neutral4
            )

            Spacer(modifier = Modifier.height(spacing.xLarge))

            BaseTextField(
                value = state.username,
                onValueChange = { value -> onEvent(SignInScreenEvent.OnUsernameChanged(value)) },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(72.dp),
                placeholder = stringResource(resource = Res.string.hint_email_username)
            )

            Spacer(modifier = Modifier.height(spacing.medium))

            PasswordTextField(
                password = state.password,
                onPasswordValueChange = { value -> onEvent(SignInScreenEvent.OnPasswordChanged(value)) },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(72.dp)
            )

            Spacer(modifier = Modifier.height(spacing.medium))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(
                    horizontalArrangement = Arrangement.Start,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Checkbox(
                        isChecked = state.rememberMe,
                        onCheckedChange = { isChecked ->
                            onEvent(SignInScreenEvent.OnRememberMeChecked(isChecked))
                        },
                        modifier = Modifier.size(24.dp)
                    )
                    Spacer(modifier = Modifier.width(spacing.xSmall))
                    Text(
                        text = stringResource(resource = Res.string.remember_me),
                        style = typography.text2.regular,
                        color = colors.neutral2
                    )
                }

                TextButton(
                    onClick = {
                        onEvent(SignInScreenEvent.OnForgotPasswordClicked)
                    },
                    colors = ButtonDefaults.textButtonColors(
                        contentColor = colors.neutral2
                    )
                ) {
                    Text(
                        text = stringResource(resource = Res.string.button_forgot_password),
                        style = typography.text2.regular
                    )
                }
            }

            Spacer(modifier = Modifier.height(spacing.xLarge))

            PrimaryButton(
                text = stringResource(resource = Res.string.button_login),
                onClick = {
                    onEvent(SignInScreenEvent.OnLoginWithGmailClicked)
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .requiredHeight(56.dp)
            )

            Spacer(modifier = Modifier.height(spacing.medium))

            Text(
                text = stringResource(resource = Res.string.other),
                style = typography.text1.regular,
                color = colors.neutral2,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )

            Spacer(modifier = Modifier.height(spacing.medium))

            OutlinedButton(
                text = stringResource(resource = Res.string.button_login_with_gmail),
                onClick = {
                    onEvent(SignInScreenEvent.OnLoginWithGmailClicked)
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .requiredHeight(56.dp),
                leadingIcon = {
                    Icon(
                        painter = painterResource(resource = Res.drawable.ic_google),
                        contentDescription = stringResource(resource = Res.string.button_login_with_gmail),
                        modifier = Modifier.padding(end = spacing.xSmall),
                        tint = Color.Unspecified
                    )
                }
            )

            Spacer(modifier = Modifier.height(spacing.medium))

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                contentAlignment = Alignment.BottomCenter
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = stringResource(resource = Res.string.register_part_first),
                        style = typography.text1.regular,
                        color = colors.neutral2,
                        modifier = Modifier
                    )

                    TextButton(
                        onClick = {
                            onEvent(SignInScreenEvent.OnRegisterClicked)
                        },
                    ) {
                        Text(
                            text = stringResource(resource = Res.string.button_register),
                            style = typography.text1.regular,
                            color = colors.primary.main
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(spacing.large))
        }
    }
}

@Preview
@Composable
private fun SignInScreenPreview() {
    DragonFlyTheme {
        SignInScreen(
            state = SignInScreenState(),
            onEvent = {}
        )
    }
}
