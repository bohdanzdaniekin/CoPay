package ui.screen.auth.signin.code

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import dragonfly.composeapp.generated.resources.Res
import dragonfly.composeapp.generated.resources.enter_security_code
import dragonfly.composeapp.generated.resources.ic_lock
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.viewmodel.koinViewModel
import ui.component.securitycode.SecurityCode
import ui.component.securitycode.SecurityCodeState
import ui.entitiy.auth.security.code.SecurityCodeScreenEffect
import ui.entitiy.auth.security.code.SecurityCodeScreenEvent
import ui.entitiy.auth.security.code.SecurityCodeScreenState
import ui.screen.main.MainScreen
import ui.theme.DragonFlyTheme
import utils.extensions.collectAsEffect
import utils.extensions.collectAsStateWithLifecycle

class SecurityCodeScreen : Screen {

        @Composable
        override fun Content() {
            val viewModel = koinViewModel<SecurityCodeViewModel>()
            val state by viewModel.state.collectAsStateWithLifecycle()

            val navigator = LocalNavigator.currentOrThrow

            viewModel.effect.collectAsEffect { effect ->
                when (effect) {
                    is SecurityCodeScreenEffect.NavigateBack -> {
                        navigator.pop()
                    }
                    is SecurityCodeScreenEffect.NavigateForward -> {
                        navigator.replaceAll(MainScreen())
                    }
                }
            }

            SecurityCodeScreen(
                state = state,
                onEvent = viewModel::onEvent
            )
        }
}

@Composable
private fun SecurityCodeScreen(
    state: SecurityCodeScreenState,
    onEvent: (SecurityCodeScreenEvent) -> Unit,
    modifier: Modifier = Modifier
) {
    SecurityCodeScreen(
        state = state.securityCode,
        onStateChanged = { securityCode ->
            onEvent(SecurityCodeScreenEvent.SecurityCodeChanged(securityCode))
        },
        modifier = modifier
    )
}

@Composable
private fun SecurityCodeScreen(
    state: SecurityCodeState,
    onStateChanged: (SecurityCodeState) -> Unit,
    modifier: Modifier = Modifier
) {
    Scaffold(
        modifier = modifier.fillMaxSize()
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            val spacing = DragonFlyTheme.spacing
            val typography = DragonFlyTheme.typography
            val colors = DragonFlyTheme.colors

            Image(
                painter = painterResource(resource = Res.drawable.ic_lock),
                contentDescription = null
            )

            Spacer(modifier = Modifier.height(spacing.xLarge))

            Text(
                text = stringResource(resource = Res.string.enter_security_code),
                style = typography.subtitle2.regular,
                color = colors.neutral2
            )

            Spacer(modifier = Modifier.height(spacing.medium))

            SecurityCode(state = state, onStateChanged = onStateChanged)

            Spacer(modifier = Modifier.height(spacing.medium))
        }
    }
}

@Preview
@Composable
private fun SecurityCodeScreenPreview() {
    DragonFlyTheme {
        SecurityCodeScreen(
            state = SecurityCodeState("1"),
            onStateChanged = {}
        )
    }
}
