package ui.screen.welcome

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import dragonfly.composeapp.generated.resources.Res
import dragonfly.composeapp.generated.resources.app_name_title
import dragonfly.composeapp.generated.resources.ic_logo
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel
import ui.entitiy.welcome.WelcomeScreenEffect
import ui.entitiy.welcome.WelcomeScreenEvent
import ui.entitiy.welcome.WelcomeScreenState
import ui.screen.auth.signin.SignInScreen
import ui.theme.DragonFlyTheme
import utils.extensions.collectAsEffect
import utils.extensions.collectAsStateWithLifecycle

class WelcomeScreen : Screen {

    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow

        val viewModel = koinViewModel<WelcomeViewModel>()
        val state by viewModel.state.collectAsStateWithLifecycle()

        viewModel.effect.collectAsEffect { effect ->
            when (effect) {
                is WelcomeScreenEffect.NavigateToAuth -> {
                    navigator.push(SignInScreen())
                }
                is WelcomeScreenEffect.NavigateToHome -> {
                    // Navigate to SignUpScreen
                }
            }
        }

        WelcomeScreen(state = state, onEvent = viewModel::onEvent)
    }
}

@Composable
private fun WelcomeScreen(
    state: WelcomeScreenState,
    onEvent: (WelcomeScreenEvent) -> Unit
) {
    LaunchedEffect(Unit) {
        onEvent(WelcomeScreenEvent.OnInit)
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(DragonFlyTheme.colors.primary.main),
        contentAlignment = Alignment.Center
    ) {
        Icon(
            modifier = Modifier.size(128.dp),
            painter = painterResource(resource = Res.drawable.ic_logo),
            contentDescription = stringResource(resource = Res.string.app_name_title),
            tint = Color.White
        )
    }
}