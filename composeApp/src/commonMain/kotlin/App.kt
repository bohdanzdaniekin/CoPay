import androidx.compose.runtime.Composable
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.transitions.CrossfadeTransition
import org.jetbrains.compose.ui.tooling.preview.Preview
import ui.screen.welcome.WelcomeScreen
import ui.theme.DragonFlyTheme

@Composable
@Preview
fun App() {
    DragonFlyTheme {
        Navigator(WelcomeScreen()) { navigator ->
            CrossfadeTransition(navigator = navigator)
        }
    }
}